package salek664.lucky_charm.config;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import oshi.util.tuples.Pair;
import salek664.lucky_charm.loot.hyper.LuckyCharmArchaeologyHyperModifiers;
import salek664.lucky_charm.loot.hyper.LuckyCharmChestHyperModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmArcheologyLootTableModifiers;
import salek664.lucky_charm.loot.non_hyper.LuckyCharmChestLootTableModifiers;

import java.util.List;
import java.util.Map;

public class LuckyCharmLootTableLoader {
    public static void loadSettings(Pair<Map<LuckyCharmConfigReader.Options, Boolean>, List<String>> config) {
        boolean doHyperLoot = config.getA().get(LuckyCharmConfigReader.Options.HyperLoot);
        boolean replaceChestLootTables = config.getA().get(LuckyCharmConfigReader.Options.Chest);
        boolean replaceArchaeologyLootTables = config.getA().get(LuckyCharmConfigReader.Options.Archaeology);
        List<String> excepts = config.getB();
        LootTableEvents.REPLACE.register((key, original, source) -> {
            if (doHyperLoot) {
                return LuckyCharmArchaeologyHyperModifiers.attemptReplace(key, source, replaceArchaeologyLootTables, excepts)
                        .orElse(LuckyCharmChestHyperModifiers.attemptReplace(key, source, replaceChestLootTables, excepts)
                                .orElse(original));
            } else {
                return LuckyCharmArcheologyLootTableModifiers.attemptReplace(key, source, replaceArchaeologyLootTables, excepts)
                        .orElse(LuckyCharmChestLootTableModifiers.attemptReplace(key, source, replaceChestLootTables, excepts)
                                .orElse(original));
            }
        });
    }
}
