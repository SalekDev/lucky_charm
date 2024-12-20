package salek664.lucky_charm.config;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import salek664.lucky_charm.LuckyCharm;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class LuckyCharmConfigReader implements IMixinConfigPlugin {
    private static final String text = "[ConfigReader]: ";
    public enum Options {
        HyperLoot(true), Chest(true), Archaeology(true);
        private boolean enabled;
        Options(boolean enabled) {
            this.enabled = enabled;
        }
    }
    @Override
    public void onLoad(String mixinPackage) {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("lucky_charm.txt");
        try {
            if (!Files.exists(configPath)) {
                LuckyCharm.LOGGER.info(text + "No config file existed, writing one instead");
                Files.createFile(configPath);
                Files.write(configPath, List.of(
                        "doHyperLoot: true",
                        "replaceChestLootTables: true",
                        "replaceArchaeologyLootTables: true"
                ), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } else {
                LuckyCharm.LOGGER.info(text + "Reading options from config");
                List<String> lines = Files.readAllLines(configPath);
                List<Options> uncheckedOptions = new ArrayList<>(Arrays.asList(Options.values()));
                for (int i = 0; (i < 20) && !uncheckedOptions.isEmpty() && lines.size() > i; i++) {
                    for (Options option : uncheckedOptions) {
                        if (option.equals(Options.HyperLoot) && lines.get(i).replace(" ", "").toUpperCase()
                                .contains("DO" + option.toString().toUpperCase() + ":FALSE")) {
                            option.enabled = false;
                            uncheckedOptions.remove(option);
                            break;
                        } else if (lines.get(i).replace(" ", "").toUpperCase()
                                .contains("REPLACE" + option.toString().toUpperCase() + "LOOTTABLES:FALSE")) {
                            option.enabled = false;
                            uncheckedOptions.remove(option);
                            break;
                        }
                    }
                }
                for (Options option : Options.values()) {
                    if (option.equals(Options.HyperLoot)) {
                        Files.writeString(configPath, "doHyperLoot: " + Options.HyperLoot.enabled, StandardOpenOption.TRUNCATE_EXISTING);
                    } else {
                        Files.writeString(configPath, "\nreplace" + option + "LootTables: " + option.enabled, StandardOpenOption.APPEND);
                    }
                }
            }
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }
    }
    public static Map<Options, Boolean> getCurrentConfig() {
        ImmutableMap.Builder<Options, Boolean> mapBuilder = ImmutableMap.builder();
        for (Options option : Options.values()) {
            mapBuilder.put(option, option.enabled);
        }
        return mapBuilder.build();
    }
    @Override
    public String getRefMapperConfig() {
        return null;
    }
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean isTarget = Objects.equals(mixinClassName, "salek664.lucky_charm.mixin.LootPool$BuilderMixin")
                || Objects.equals(mixinClassName, "salek664.lucky_charm.mixin.LootPoolMixin");
        return !isTarget || Options.HyperLoot.enabled;
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
