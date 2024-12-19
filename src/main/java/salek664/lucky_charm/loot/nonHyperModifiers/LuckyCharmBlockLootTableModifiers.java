package salek664.lucky_charm.loot.nonHyperModifiers;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import salek664.lucky_charm.item.LuckyCharmItems;

public class LuckyCharmBlockLootTableModifiers {
    public static void registerModifiers() {
        LootTableEvents.MODIFY.register(((key, tableBuilder, source) -> {
            if (Blocks.SHORT_GRASS.getLootTableKey() == key || Blocks.TALL_GRASS.getLootTableKey() == key) {
                tableBuilder
                        .pool(
                        LootPool.builder()
                                .conditionally(RandomChanceLootCondition.builder(0.0625f))
                                .with(
                                        ItemEntry.builder(LuckyCharmItems.THREELEAF_CLOVER)
                                                .weight(6).quality(2)
                                )
                                .with(
                                        ItemEntry.builder(LuckyCharmItems.FOURLEAF_CLOVER)
                                                .weight(1).quality(4)
                                )
                        );
            }
        }));
    }
}
