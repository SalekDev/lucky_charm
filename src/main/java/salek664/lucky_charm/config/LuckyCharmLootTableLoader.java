package salek664.lucky_charm.config;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import oshi.util.tuples.Pair;
import salek664.lucky_charm.loot.hyper.LuckyCharmArchaeologyHyperModifiers;
import salek664.lucky_charm.loot.hyper.LuckyCharmContainerHyperModifiers;
import salek664.lucky_charm.loot.hyper.LuckyCharmTrialVaultsHyperModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmArcheologyLootTableModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmContainerLootTableModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmTrialVaultsModifiers;

import java.util.List;
import java.util.Map;

public class LuckyCharmLootTableLoader {
    public static void loadSettings(Pair<Map<LuckyCharmConfigReader.Options, Boolean>, List<String>> config) {
        boolean doHyperLoot = config.getA().get(LuckyCharmConfigReader.Options.HyperLoot);
        boolean replaceChestLootTables = config.getA().get(LuckyCharmConfigReader.Options.Chest);
        boolean replaceArchaeologyLootTables = config.getA().get(LuckyCharmConfigReader.Options.Archaeology);
        boolean replaceTrialChambersLootTables = config.getA().get(LuckyCharmConfigReader.Options.TrialChambersVaults);
        List<String> excepts = config.getB();
        LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            if (doHyperLoot) {
                return LuckyCharmArchaeologyHyperModifiers.attemptReplace(key, source, replaceArchaeologyLootTables, excepts, registries)
                        .orElse(LuckyCharmContainerHyperModifiers.attemptReplace(key, source, replaceChestLootTables, excepts, registries)
                                .orElse(LuckyCharmTrialVaultsHyperModifiers.attemptReplace(key, source, replaceTrialChambersLootTables, excepts, registries)
                                        .orElse(original)));
            } else {
                return LuckyCharmArcheologyLootTableModifiers.attemptReplace(key, source, replaceArchaeologyLootTables, excepts, registries)
                        .orElse(LuckyCharmContainerLootTableModifiers.attemptReplace(key, source, replaceChestLootTables, excepts, registries)
                                .orElse(LuckyCharmTrialVaultsModifiers.attemptReplace(key, source, replaceTrialChambersLootTables, excepts, registries)
                                        .orElse(original)));
            }
        });
    }
}
