package salek664.lucky_charm.loot.hyper;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class LuckyCharmTrialVaultsHyperModifiers {
    public static final ImmutableMap<RegistryKey<LootTable>, Function<RegistryWrapper.WrapperLookup, LootTable.Builder>> LOOTTABLES;
    static {
        LOOTTABLES = ImmutableMap.<RegistryKey<LootTable>, Function<RegistryWrapper.WrapperLookup, LootTable.Builder>>builder()
                .put(LootTables.TRIAL_CHAMBERS_REWARD_RARE_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardRareChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_COMMON_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardCommonChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_UNIQUE_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardUniqueChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardOminousRareChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardOminousCommonChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardOminousUniqueChest)
                .put(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_CHEST, LuckyCharmTrialVaultsHyperModifiers::trialChambersRewardOminousChest)
                .put(LootTables.TRIAL_CHAMBER_CONSUMABLES_SPAWNER, LuckyCharmTrialVaultsHyperModifiers::trialChamberConsumablesSpawner)
                .put(LootTables.OMINOUS_TRIAL_CHAMBER_CONSUMABLES_SPAWNER, LuckyCharmTrialVaultsHyperModifiers::ominousTrialChamberConsumablesSpawner)
                .put(LootTables.TRIAL_CHAMBER_ITEMS_TO_DROP_WHEN_OMINOUS_SPAWNER, LuckyCharmTrialVaultsHyperModifiers::trialChambersItemsToDropWhenOminousSpawner)
                .build();
    }
    public static Optional<LootTable> attemptReplace(RegistryKey<LootTable> key, LootTableSource source, boolean enabled, List<String> excepts,
                                                     RegistryWrapper.WrapperLookup registries) {
        String k = key.getValue().getPath();
        for (String except : excepts) {
            if (k.endsWith(except)) {
                enabled = !enabled;
                break;
            }
        }
        LootTable table = null;
        if (enabled && source.isBuiltin()) {
            Function<RegistryWrapper.WrapperLookup, LootTable.Builder> func = LOOTTABLES.get(key);
            table = func != null ? func.apply(registries).build() : null;
        }
        return Optional.ofNullable(table);
    }
    public static LootTable.Builder trialChambersRewardRareChest(RegistryWrapper.WrapperLookup registries) {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.EMERALD).weight(3).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.SHIELD).weight(3).quality(0).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5F, 1.0F))))
                                .with(ItemEntry.builder(Items.BOW).weight(3).quality(0).apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(5.0F, 15.0F))))
                                .with(
                                        ItemEntry.builder(Items.CROSSBOW).weight(2).quality(0).apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(5.0F, 20.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_AXE).weight(2).quality(0).apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 10.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_CHESTPLATE)
                                                .weight(2).quality(0)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 10.0F)))
                                )
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(2).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(2).quality(3)
                                                .apply(
                                                        new EnchantRandomlyLootFunction.Builder()
                                                                .options(
                                                                        RegistryEntryList.of(
                                                                                impl.getOrThrow(Enchantments.SHARPNESS),
                                                                                impl.getOrThrow(Enchantments.BANE_OF_ARTHROPODS),
                                                                                impl.getOrThrow(Enchantments.EFFICIENCY),
                                                                                impl.getOrThrow(Enchantments.FORTUNE),
                                                                                impl.getOrThrow(Enchantments.SILK_TOUCH),
                                                                                impl.getOrThrow(Enchantments.FEATHER_FALLING)
                                                                        )
                                                                )
                                                )
                                )
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(2).quality(3)
                                                .apply(
                                                        new EnchantRandomlyLootFunction.Builder()
                                                                .options(
                                                                        RegistryEntryList.of(
                                                                                impl.getOrThrow(Enchantments.RIPTIDE),
                                                                                impl.getOrThrow(Enchantments.LOYALTY),
                                                                                impl.getOrThrow(Enchantments.CHANNELING),
                                                                                impl.getOrThrow(Enchantments.IMPALING),
                                                                                impl.getOrThrow(Enchantments.MENDING)
                                                                        )
                                                                )
                                                )
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                                .weight(1).quality(3)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(5.0F, 15.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_AXE)
                                                .weight(1).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(5.0F, 15.0F)))
                                )
                );
    }
    public static LootTable.Builder trialChambersRewardCommonChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.ARROW).weight(4).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(
                                        ItemEntry.builder(Items.TIPPED_ARROW)
                                                .weight(4).quality(1)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.POISON))
                                )
                                .with(ItemEntry.builder(Items.EMERALD).weight(4).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.WIND_CHARGE).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.HONEY_BOTTLE).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(
                                        ItemEntry.builder(Items.OMINOUS_BOTTLE)
                                                .weight(2).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetOminousBottleAmplifierLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                                .with(ItemEntry.builder(Items.WIND_CHARGE).weight(1).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(1).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
    public static LootTable.Builder trialChambersRewardUniqueChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(4).quality(2))
                                .with(ItemEntry.builder(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).quality(2))
                                .with(ItemEntry.builder(Items.GUSTER_BANNER_PATTERN).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_PRECIPICE).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.TRIDENT).weight(1).quality(1))
                );
    }
    public static LootTable.Builder trialChambersRewardChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_RARE_CHEST).weight(8).quality(1))
                                .with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_COMMON_CHEST).weight(2).quality(0))
                )
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0F, 3.0F)).with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_COMMON_CHEST)))
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(3).quality(0))
                                .with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_UNIQUE_CHEST).quality(2))
                );
    }
    public static LootTable.Builder trialChambersRewardOminousRareChest(RegistryWrapper.WrapperLookup registries) {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.EMERALD_BLOCK).weight(5).quality(3))
                                .with(ItemEntry.builder(Items.IRON_BLOCK).weight(4).quality(2))
                                .with(
                                        ItemEntry.builder(Items.CROSSBOW).weight(4).quality(1).apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(5.0F, 20.0F)))
                                )
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(3).quality(2))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_AXE)
                                                .weight(3).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 20.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                                .weight(3).quality(3)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 20.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(2).quality(3)
                                                .apply(
                                                        new EnchantRandomlyLootFunction.Builder()
                                                                .options(
                                                                        RegistryEntryList.of(
                                                                                impl.getOrThrow(Enchantments.KNOCKBACK),
                                                                                impl.getOrThrow(Enchantments.PUNCH),
                                                                                impl.getOrThrow(Enchantments.SMITE),
                                                                                impl.getOrThrow(Enchantments.LOOTING),
                                                                                impl.getOrThrow(Enchantments.MULTISHOT)
                                                                        )
                                                                )
                                                )
                                )
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(2).quality(3)
                                                .apply(
                                                        new EnchantRandomlyLootFunction.Builder().options(RegistryEntryList.of(impl.getOrThrow(Enchantments.BREACH), impl.getOrThrow(Enchantments.DENSITY)))
                                                )
                                )
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(2).quality(3)
                                                .apply(new SetEnchantmentsLootFunction.Builder().enchantment(impl.getOrThrow(Enchantments.WIND_BURST), ConstantLootNumberProvider.create(1.0F)))
                                )
                                .with(ItemEntry.builder(Items.DIAMOND_BLOCK).weight(1).quality(3))
                );
    }
    public static LootTable.Builder trialChambersRewardOminousCommonChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.EMERALD).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.WIND_CHARGE).weight(4).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 12.0F))))
                                .with(
                                        ItemEntry.builder(Items.TIPPED_ARROW)
                                                .weight(3).quality(1)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRONG_SLOWNESS))
                                )
                                .with(ItemEntry.builder(Items.DIAMOND).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                                .with(
                                        ItemEntry.builder(Items.OMINOUS_BOTTLE)
                                                .weight(1).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetOminousBottleAmplifierLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F)))
                                )
                );
    }
    public static LootTable.Builder trialChambersRewardOminousUniqueChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(3).quality(3))
                                .with(ItemEntry.builder(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).quality(2))
                                .with(ItemEntry.builder(Items.FLOW_BANNER_PATTERN).weight(2).quality(2))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CREATOR).weight(1).quality(2))
                                .with(ItemEntry.builder(Items.HEAVY_CORE).weight(1).quality(3))
                );
    }
    public static LootTable.Builder trialChambersRewardOminousChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE_CHEST).weight(8).quality(1))
                                .with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON_CHEST).weight(2).quality(0))
                )
                .pool(
                        LootPool.builder().rolls(UniformLootNumberProvider.create(1.0F, 3.0F)).with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON_CHEST))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(RandomChanceLootCondition.builder(0.75F))
                                .with(LootTableEntry.builder(LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE_CHEST))
                );
    }
    public static LootTable.Builder trialChamberConsumablesSpawner(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.COOKED_CHICKEN).quality(0).weight(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.BREAD).quality(0).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BAKED_POTATO).quality(0).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(
                                        ItemEntry.builder(Items.POTION).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.REGENERATION))
                                )
                                .with(
                                        ItemEntry.builder(Items.POTION).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.SWIFTNESS))
                                )
                );
    }
    public static LootTable.Builder ominousTrialChamberConsumablesSpawner(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.COOKED_BEEF).quality(0).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.BAKED_POTATO).quality(0).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).quality(1).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(
                                        ItemEntry.builder(Items.POTION).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.REGENERATION))
                                )
                                .with(
                                        ItemEntry.builder(Items.POTION).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRENGTH))
                                )
                );
    }
    public static LootTable.Builder trialChambersItemsToDropWhenOminousSpawner(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.WIND_CHARGED))
                                                .quality(0)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.OOZING))
                                                .quality(0)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.WEAVING))
                                                .quality(0)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.INFESTED))
                                                .quality(0)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRENGTH))
                                                .quality(1)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.SWIFTNESS))
                                                .quality(1)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.SLOW_FALLING))
                                                .quality(1)
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.ARROW).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.ARROW)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.POISON))
                                                .quality(0)
                                )
                                .with(
                                        ItemEntry.builder(Items.ARROW)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRONG_SLOWNESS))
                                                .quality(0)
                                )
                                .with(ItemEntry.builder(Items.FIRE_CHARGE).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WIND_CHARGE).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
}