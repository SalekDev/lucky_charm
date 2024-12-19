package salek664.lucky_charm.config;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LootMixinDisabler implements IMixinConfigPlugin {
    public static boolean doHyperLoot = true;
    @Override
    public void onLoad(String mixinPackage) {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("lucky_charm.txt");
        try {
            if (!Files.exists(configPath)) {
                Files.createFile(configPath);
                Files.write(configPath, List.of("doHyperLoot: true"), StandardCharsets.UTF_8);
            } else {
                List<String> lines = Files.readAllLines(configPath);
                String line0 = lines.get(0).replace(" ", "").toLowerCase();
                if (line0.startsWith("dohyperloot:false")) {
                    doHyperLoot = false;
                } else {
                    Files.write(configPath, List.of("doHyperLoot: true"), StandardCharsets.UTF_8);
                }
            }
        } catch (Exception var3) {
            var3.fillInStackTrace();
        }
    }
    @Override
    public String getRefMapperConfig() {
        return null;
    }
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean isTarget = Objects.equals(mixinClassName, "salek664.lucky_charm.mixin.LootTableBuilderMixin")
                || Objects.equals(mixinClassName, "salek664.lucky_charm.mixin.LootTableMixin");
        return !isTarget || doHyperLoot;
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
