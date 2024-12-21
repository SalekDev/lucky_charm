package salek664.lucky_charm.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import oshi.util.tuples.Pair;
import salek664.lucky_charm.LuckyCharm;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class LuckyCharmConfigReader implements IMixinConfigPlugin {
    private static final String TEXT = "[ConfigReader]: ";
    private static final ImmutableList.Builder<String> EXCEPT = ImmutableList.builder();
    public enum Options {
        HyperLoot(true), Chest(true), Archaeology(true);
        private boolean isEnabled;
        Options(boolean isEnabled) {
            this.isEnabled = isEnabled;
        }
    }
    @Override
    public void onLoad(String mixinPackage) {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("lucky_charm.txt");
        try {
            if (!Files.exists(configPath)) {
                LuckyCharm.LOGGER.info(TEXT + "No config file existed, writing one instead");
                Files.createFile(configPath);
                Files.write(configPath, List.of(
                        "doHyperLoot: true",
                        "replaceChestLootTables: true",
                        "replaceArchaeologyLootTables: true",
                        "exceptions: []"
                ), StandardCharsets.UTF_8, StandardOpenOption.WRITE);
                LuckyCharm.LOGGER.info(TEXT + "Successfully wrote config file");
            } else {
                LuckyCharm.LOGGER.info(TEXT + "Reading options from config");
                readConfigFile(configPath);
                LuckyCharm.LOGGER.info(TEXT + "Successfully read options from config");
                writeConfigFile(configPath);
                LuckyCharm.LOGGER.info(TEXT + "Successfully re-wrote config file");
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
    private static void readConfigFile(Path configPath) throws Exception {
        List<String> lines = Files.readAllLines(configPath);
        List<Options> uncheckedOptions = new ArrayList<>(Arrays.asList(Options.values()));
        boolean readingExcepts = false;
        boolean exceptStart;
        for (int i = 0; (i < 20) && !uncheckedOptions.isEmpty() && lines.size() > i; i++) {
            String line = lines.get(i).replace(" ", "");
            String lineLower = line.toLowerCase();
            int bracketIndex = lineLower.indexOf("exceptions:[");
            if (!readingExcepts && bracketIndex > -1) {
                readingExcepts = true;
            }
            if (!readingExcepts) {
                for (Options option : uncheckedOptions) {
                    if (option.equals(Options.HyperLoot) && lineLower.contains("do" + option.toString().toLowerCase() + ":false")) {
                        option.isEnabled = false;
                        uncheckedOptions.remove(option);
                        break;
                    } else if (lineLower.contains("replace" + option.toString().toLowerCase() + "loottables:false")) {
                        option.isEnabled = false;
                        uncheckedOptions.remove(option);
                        break;
                    }
                }
            }
            if (readingExcepts) {
                exceptStart = bracketIndex > -1;
                boolean foundSeparator;
                String inBrackets = lineLower;
                do {
                    inBrackets = inBrackets.substring(bracketIndex + (exceptStart ? 12 : 1));
                    bracketIndex = 0;
                    int endIndex = inBrackets.indexOf("]");
                    int separatorIndex = inBrackets.indexOf(",");
                    foundSeparator = separatorIndex > -1;
                    int lookIndex = foundSeparator ? separatorIndex : (endIndex > -1 ? endIndex : inBrackets.length());
                    String except = inBrackets.substring(bracketIndex, lookIndex);
                    if (except.length() > 0) EXCEPT.add(except);
                    bracketIndex = except.length();
                    exceptStart = false;
                } while (foundSeparator);
            }
        }
    }
    private static void writeConfigFile(Path configPath) throws Exception {
        for (Options option : Options.values()) {
            if (option.equals(Options.HyperLoot)) {
                Files.writeString(configPath, "doHyperLoot: " + Options.HyperLoot.isEnabled, StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.writeString(configPath, "\nreplace" + option + "LootTables: " + option.isEnabled, StandardOpenOption.APPEND);
            }
        }
        List<String> excepts = EXCEPT.build();
        Files.writeString(configPath, "\nexceptions: [", StandardOpenOption.APPEND);
        int s = excepts.size();
        if (s > 0) {
            Files.writeString(configPath, excepts.get(0), StandardOpenOption.APPEND);
        }
        if (s > 1) {
            for (int i = 1; i < s; i++) {
                Files.writeString(configPath, ", " + excepts.get(i), StandardOpenOption.APPEND);
            }
        }
        Files.writeString(configPath, "]", StandardOpenOption.APPEND);
    }
    public static Pair<Map<Options, Boolean>, List<String>> getCurrentConfig() {
        ImmutableMap.Builder<Options, Boolean> mapBuilder = ImmutableMap.builder();
        for (Options option : Options.values()) {
            mapBuilder.put(option, option.isEnabled);
        }
        return new Pair<>(mapBuilder.build(), EXCEPT.build());
    }
    public static List<String> getExcepts() {
        return EXCEPT.build();
    }
    @Override
    public String getRefMapperConfig() {
        return null;
    }
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean isTarget = Objects.equals(mixinClassName, "salek664.lucky_charm.mixin.LootPool$BuilderMixin")
                || Objects.equals(mixinClassName, "salek664.lucky_charm.mixin.loot.LootPoolMixin");
        return !isTarget || Options.HyperLoot.isEnabled;
    }
    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
    @Override
    public List<String> getMixins() {
        return null;
    }
    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
