package salek664.lucky_charm.config;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import salek664.lucky_charm.loot.hyper.LuckyCharmArchaeologyHyperModifiers;
import salek664.lucky_charm.loot.hyper.LuckyCharmChestHyperModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmArcheologyLootTableModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmChestLootTableModifiers;

import java.util.Map;

public class LuckyCharmLootTableManager {
    public static void initializeSettings(Map<LuckyCharmConfigReader.Options, Boolean> config) {
        boolean doHyperLoot = config.get(LuckyCharmConfigReader.Options.HyperLoot);
        boolean replaceChestLootTables = config.get(LuckyCharmConfigReader.Options.Chest);
        boolean replaceArchaeologyLootTables = config.get(LuckyCharmConfigReader.Options.Archaeology);
        LootTableEvents.REPLACE.register((key, original, source) -> {
            if (doHyperLoot) {
                return LuckyCharmArchaeologyHyperModifiers.attemptReplace(key, source, replaceArchaeologyLootTables)
                        .orElse(LuckyCharmChestHyperModifiers.attemptReplace(key, source, replaceChestLootTables)
                                .orElse(original));
            } else {
                return LuckyCharmArcheologyLootTableModifiers.attemptReplace(key, source, replaceArchaeologyLootTables)
                        .orElse(LuckyCharmChestLootTableModifiers.attemptReplace(key, source, replaceChestLootTables)
                                .orElse(original));
            }
        });
    }
}
