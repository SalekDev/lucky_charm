package salek664.lucky_charm.loot.non_hyper;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.text.Text;
import salek664.lucky_charm.item.LuckyCharmItems;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class LuckyCharmContainerLootTableModifiers {
    public static final ImmutableMap<RegistryKey<LootTable>, Function<RegistryWrapper.WrapperLookup, LootTable.Builder>> LOOTTABLES;
    static {
        LOOTTABLES = ImmutableMap.<RegistryKey<LootTable>, Function<RegistryWrapper.WrapperLookup, LootTable.Builder>>builder()
                .put(LootTables.ABANDONED_MINESHAFT_CHEST, LuckyCharmContainerLootTableModifiers::abandonedMineshaftChest)
                .put(LootTables.BASTION_BRIDGE_CHEST, LuckyCharmContainerLootTableModifiers::bastionBridgeChest)
                .put(LootTables.BASTION_HOGLIN_STABLE_CHEST, LuckyCharmContainerLootTableModifiers::bastionHoglinStableChest)
                .put(LootTables.BASTION_OTHER_CHEST, LuckyCharmContainerLootTableModifiers::bastionOtherChest)
                .put(LootTables.BASTION_TREASURE_CHEST, LuckyCharmContainerLootTableModifiers::bastionTreasureChest)
                .put(LootTables.BURIED_TREASURE_CHEST, LuckyCharmContainerLootTableModifiers::buriedTreasureChest)
                .put(LootTables.ANCIENT_CITY_CHEST, LuckyCharmContainerLootTableModifiers::ancientCityChest)
                .put(LootTables.ANCIENT_CITY_ICE_BOX_CHEST, LuckyCharmContainerLootTableModifiers::ancientCityIceboxChest)
                .put(LootTables.DESERT_PYRAMID_CHEST, LuckyCharmContainerLootTableModifiers::desertPyramidChest)
                .put(LootTables.END_CITY_TREASURE_CHEST, LuckyCharmContainerLootTableModifiers::endCityTreasureChest)
                .put(LootTables.IGLOO_CHEST_CHEST, LuckyCharmContainerLootTableModifiers::iglooChest)
                .put(LootTables.JUNGLE_TEMPLE_CHEST, LuckyCharmContainerLootTableModifiers::jungleTempleChest)
                .put(LootTables.NETHER_BRIDGE_CHEST, LuckyCharmContainerLootTableModifiers::netherBridgeChest)
                .put(LootTables.PILLAGER_OUTPOST_CHEST, LuckyCharmContainerLootTableModifiers::pillagerOutpostChest)
                .put(LootTables.SHIPWRECK_MAP_CHEST, LuckyCharmContainerLootTableModifiers::shipwreckMapChest)
                .put(LootTables.SHIPWRECK_SUPPLY_CHEST, LuckyCharmContainerLootTableModifiers::shipwreckSupplyChest)
                .put(LootTables.SHIPWRECK_TREASURE_CHEST, LuckyCharmContainerLootTableModifiers::shipwreckTreasureChest)
                .put(LootTables.SIMPLE_DUNGEON_CHEST, LuckyCharmContainerLootTableModifiers::simpleDungeonChest)
                .put(LootTables.STRONGHOLD_CORRIDOR_CHEST, LuckyCharmContainerLootTableModifiers::strongholdCorridorChest)
                .put(LootTables.STRONGHOLD_CROSSING_CHEST, LuckyCharmContainerLootTableModifiers::strongholdCrossingChest)
                .put(LootTables.STRONGHOLD_LIBRARY_CHEST, LuckyCharmContainerLootTableModifiers::strongholdLibraryChest)
                .put(LootTables.UNDERWATER_RUIN_BIG_CHEST, LuckyCharmContainerLootTableModifiers::underwaterRuinBigChest)
                .put(LootTables.UNDERWATER_RUIN_SMALL_CHEST, LuckyCharmContainerLootTableModifiers::underwaterRuinSmallChest)
                .put(LootTables.VILLAGE_WEAPONSMITH_CHEST, LuckyCharmContainerLootTableModifiers::villageWeaponsmithChest)
                .put(LootTables.VILLAGE_TOOLSMITH_CHEST, LuckyCharmContainerLootTableModifiers::villageToolsmithChest)
                .put(LootTables.VILLAGE_CARTOGRAPHER_CHEST, LuckyCharmContainerLootTableModifiers::villageCartographerChest)
                .put(LootTables.VILLAGE_MASON_CHEST, LuckyCharmContainerLootTableModifiers::villageMasonChest)
                .put(LootTables.VILLAGE_ARMORER_CHEST, LuckyCharmContainerLootTableModifiers::villageArmorerChest)
                .put(LootTables.VILLAGE_SHEPARD_CHEST, LuckyCharmContainerLootTableModifiers::villageShepardChest)
                .put(LootTables.VILLAGE_BUTCHER_CHEST, LuckyCharmContainerLootTableModifiers::villageButcherChest)
                .put(LootTables.VILLAGE_FLETCHER_CHEST, LuckyCharmContainerLootTableModifiers::villageFletcherChest)
                .put(LootTables.VILLAGE_FISHER_CHEST, LuckyCharmContainerLootTableModifiers::villageFisherChest)
                .put(LootTables.VILLAGE_TANNERY_CHEST, LuckyCharmContainerLootTableModifiers::villageTanneryChest)
                .put(LootTables.VILLAGE_TEMPLE_CHEST, LuckyCharmContainerLootTableModifiers::villageTempleChest)
                .put(LootTables.VILLAGE_PLAINS_CHEST, LuckyCharmContainerLootTableModifiers::villagePlainsChest)
                .put(LootTables.VILLAGE_TAIGA_HOUSE_CHEST, LuckyCharmContainerLootTableModifiers::villageTaigaHouseChest)
                .put(LootTables.VILLAGE_SAVANNA_HOUSE_CHEST, LuckyCharmContainerLootTableModifiers::villageSavannaHouseChest)
                .put(LootTables.VILLAGE_SNOWY_HOUSE_CHEST, LuckyCharmContainerLootTableModifiers::villageSnowyHouseChest)
                .put(LootTables.VILLAGE_DESERT_HOUSE_CHEST, LuckyCharmContainerLootTableModifiers::villageDesertHouseChest)
                .put(LootTables.RUINED_PORTAL_CHEST, LuckyCharmContainerLootTableModifiers::ruinedPortalChest)
                .put(LootTables.WOODLAND_MANSION_CHEST, LuckyCharmContainerLootTableModifiers::woodlandMansionChest)
                .put(LootTables.TRIAL_CHAMBERS_CHAMBER_DISPENSER, LuckyCharmContainerLootTableModifiers::trialChambersChamberDispenser)
                .put(LootTables.TRIAL_CHAMBERS_CORRIDOR_POT, LuckyCharmContainerLootTableModifiers::trialChambersCorridorPot)
                .put(LootTables.TRIAL_CHAMBERS_SUPPLY_CHEST, LuckyCharmContainerLootTableModifiers::trialChambersSupplyChest)
                .put(LootTables.TRIAL_CHAMBERS_ENTRANCE_CHEST, LuckyCharmContainerLootTableModifiers::trialChambersEntranceChest)
                .put(LootTables.TRIAL_CHAMBERS_INTERSECTION_CHEST, LuckyCharmContainerLootTableModifiers::trialChambersIntersectionChest)
                .put(LootTables.TRIAL_CHAMBERS_INTERSECTION_BARREL_CHEST, LuckyCharmContainerLootTableModifiers::trialChambersIntersectionBarrelChest)
                .put(LootTables.TRIAL_CHAMBERS_CORRIDOR_CHEST, LuckyCharmContainerLootTableModifiers::trialChambersCorridorChest)
                .build();
    }
    public static Optional<LootTable> attemptReplace(RegistryKey<LootTable> key, LootTableSource source, boolean enabled, List<String> excepts, RegistryWrapper.WrapperLookup registries) {
        String k = key.getValue().getPath();
        for (String except : excepts) {
            if (k.endsWith(except)) {
                enabled = !enabled; //An exception does the opposite of the predefined enabled
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
    private static LootTable.Builder abandonedMineshaftChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(40).quality(3))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2).quality(2))
                                .with(ItemEntry.builder(Items.NAME_TAG).weight(60))
                                .with(ItemEntry.builder(Items.BOOK).weight(20).quality(3).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(10).quality(-1))
                                .with(EmptyEntry.builder().weight(10).quality(-2))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).quality(2))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(6).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(30).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GLOW_BERRIES).weight(30).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.MELON_SEEDS).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .with(ItemEntry.builder(Blocks.RAIL).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.POWERED_RAIL).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.DETECTOR_RAIL).weight(5).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.ACTIVATOR_RAIL).weight(5).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.TORCH).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 16.0F))))
                );
    }
    private static LootTable.Builder bastionBridgeChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Blocks.LODESTONE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(
                                        ItemEntry.builder(Items.CROSSBOW)
                                                .weight(20)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.5F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Items.SPECTRAL_ARROW).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(10.0F, 28.0F))))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 12.0F))))
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).weight(20).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(20).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).weight(20).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_CHESTPLATE)
                                                .weight(20).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_HELMET)
                                                .weight(20).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_LEGGINGS)
                                                .weight(20).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_BOOTS)
                                                .weight(20).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .weight(20).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.STRING).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.LEATHER).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.ARROW).weight(15).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(10).quality(-1))
                                .with(EmptyEntry.builder().weight(1))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(15).quality(-2))
                                .with(EmptyEntry.builder().weight(3))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(2).quality(4))
                );
    }
    private static LootTable.Builder bastionHoglinStableChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SHOVEL)
                                                .weight(30).quality(2)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .weight(24).quality(2)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.95F)))
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Items.NETHERITE_SCRAP).weight(16).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.ANCIENT_DEBRIS).weight(24).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.ANCIENT_DEBRIS).weight(10).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(24).quality(-2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).weight(32).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(20).quality(-1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .weight(20)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).weight(20).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.GLOWSTONE).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.SOUL_SAND).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.CRIMSON_NYLIUM).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(20).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.LEATHER).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.ARROW).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(20).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.PORKCHOP).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.COOKED_PORKCHOP).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.CRIMSON_FUNGUS).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.CRIMSON_ROOTS).weight(20).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(10).quality(-1))
                                .with(EmptyEntry.builder().weight(1))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(15).quality(-2))
                                .with(EmptyEntry.builder().weight(3))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(2).quality(4))
                );
    }
    private static LootTable.Builder bastionOtherChest(RegistryWrapper.WrapperLookup registries) {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .weight(12).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Items.DIAMOND_SHOVEL)
                                        .weight(12).quality(2)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.CROSSBOW)
                                                .weight(12).quality(2)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.9F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Items.ANCIENT_DEBRIS).weight(24).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.NETHERITE_SCRAP).weight(8).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.SPECTRAL_ARROW).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(10.0F, 22.0F))))
                                .with(ItemEntry.builder(Items.PIGLIN_BANNER_PATTERN).weight(18).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_PIGSTEP).weight(10).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(24).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(18).quality(-1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(20).quality(2).apply(new EnchantRandomlyLootFunction.Builder().option(impl.getOrThrow(Enchantments.SOUL_SPEED))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(2.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(
                                        ItemEntry.builder(Items.IRON_SWORD)
                                                .weight(26).quality(-2)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.9F)))
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Blocks.IRON_BLOCK)
                                        .weight(26).quality(-2)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_BOOTS)
                                                .weight(13)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(new EnchantRandomlyLootFunction.Builder().option(impl.getOrThrow(Enchantments.SOUL_SPEED)))
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .weight(13)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).weight(26).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.CROSSBOW).weight(13).quality(-2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(26).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(26).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).weight(13).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).weight(13).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).weight(13).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_LEGGINGS).weight(13).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_BOOTS).weight(13).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).weight(26).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).weight(26).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.CHAIN).weight(13).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.MAGMA_CREAM).weight(26).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Blocks.BONE_BLOCK).weight(13).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(13).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.OBSIDIAN).weight(13).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(13).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(13).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ARROW).weight(26).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.COOKED_PORKCHOP).weight(13).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(10).quality(-1))
                                .with(EmptyEntry.builder().weight(1))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(15).quality(-2))
                                .with(EmptyEntry.builder().weight(3))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(2).quality(4))
                );
    }
    private static LootTable.Builder bastionTreasureChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.17f))
                                .with(ItemEntry.builder(Items.NETHERITE_INGOT).weight(30).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.ANCIENT_DEBRIS).weight(20).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.NETHERITE_SCRAP).weight(16).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.ANCIENT_DEBRIS).weight(8).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SWORD)
                                                .weight(12)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                                .weight(12).quality(2)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_HELMET)
                                                .weight(12).quality(1)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                                .weight(12).quality(1)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_BOOTS)
                                                .weight(12)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                )
                                .with(ItemEntry.builder(Items.DIAMOND_SWORD).weight(12).quality(-2))
                                .with(ItemEntry.builder(Items.DIAMOND_CHESTPLATE).weight(10).quality(-1))
                                .with(ItemEntry.builder(Items.DIAMOND_HELMET).weight(10).quality(-2))
                                .with(ItemEntry.builder(Items.DIAMOND_BOOTS).weight(10).quality(-2))
                                .with(ItemEntry.builder(Items.DIAMOND_LEGGINGS).weight(10).quality(-1))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(4).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.SPECTRAL_ARROW).weight(17).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(12.0F, 25.0F))))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).weight(17).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.IRON_BLOCK).weight(17).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(17).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(17).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 9.0F))))
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).weight(17).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.QUARTZ).weight(17).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 23.0F))))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).weight(17).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.MAGMA_CREAM).weight(17).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(10).quality(-1))
                                .with(EmptyEntry.builder())
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    private static LootTable.Builder buriedTreasureChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.HEART_OF_THE_SEA)))
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(5.0F, 8.0F))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(40).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(20).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.TNT).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.EMERALD).weight(23).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(23).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.PRISMARINE_CRYSTALS).weight(23).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0.0F, 1.0F))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).weight(17).quality(-2))
                                .with(ItemEntry.builder(Items.IRON_SWORD).weight(17))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(2.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.COOKED_COD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.COOKED_SALMON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0.0F, 2.0F))
                                .with(ItemEntry.builder(Items.POTION))
                                .apply(SetPotionLootFunction.builder(Potions.WATER_BREATHING))
                );
    }
    private static LootTable.Builder ancientCityChest(RegistryWrapper.WrapperLookup registries) {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(5.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.5f))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(6).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(6).quality(1))
                                .with(ItemEntry.builder(Items.COMPASS).weight(12).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.SCULK_CATALYST).weight(12).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.NAME_TAG).weight(12))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_HOE)
                                                .weight(12).quality(1)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(30.0F, 50.0F)))
                                )
                                .with(ItemEntry.builder(Items.LEAD).weight(12).quality(-1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(12).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(12).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_13).weight(12).quality(-1))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(12).quality(-1))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                                .weight(12).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(30.0F, 50.0F)))
                                )
                                .with(ItemEntry.builder(Items.BOOK).weight(18).quality(3).apply(new EnchantRandomlyLootFunction.Builder().option(impl.getOrThrow(Enchantments.SWIFT_SNEAK))))
                                .with(ItemEntry.builder(Items.SCULK).weight(18).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.SCULK_SENSOR).weight(18).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.CANDLE).weight(18).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.AMETHYST_SHARD).weight(18).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(18).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GLOW_BERRIES).weight(18).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(
                                        ItemEntry.builder(Items.IRON_LEGGINGS)
                                                .weight(18).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(ItemEntry.builder(Items.ECHO_SHARD).weight(24).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.DISC_FRAGMENT_5).weight(24).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(
                                        ItemEntry.builder(Items.POTION)
                                                .weight(30).quality(-1)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRONG_REGENERATION))
                                )
                                .with(ItemEntry.builder(Items.BOOK).weight(30).quality(3).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.BOOK).weight(30).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.BONE).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.SOUL_TORCH).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(42).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 15.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(73).quality(-10))
                                .with(EmptyEntry.builder().weight(2))
                                .with(ItemEntry.builder(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE).weight(4).quality(1))
                                .with(ItemEntry.builder(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    private static LootTable.Builder ancientCityIceboxChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(4.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(
                                        ItemEntry.builder(Items.SUSPICIOUS_STEW)
                                                .weight(10).quality(1)
                                                .apply(
                                                        SetStewEffectLootFunction.builder()
                                                                .withEffect(StatusEffects.NIGHT_VISION, UniformLootNumberProvider.create(7.0F, 10.0F))
                                                                .withEffect(StatusEffects.BLINDNESS, UniformLootNumberProvider.create(5.0F, 7.0F))
                                                )
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F)))
                                )
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.BAKED_POTATO).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.PACKED_ICE).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.SNOWBALL).weight(40).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                );
    }
    private static LootTable.Builder desertPyramidChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(30).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(30).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(30).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BONE).weight(50).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.SPIDER_EYE).weight(50).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(50).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(40).quality(-4))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(30).quality(-2))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(20).quality(-1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(10))
                                .with(ItemEntry.builder(Items.BOOK).weight(40).quality(2).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(40).quality(2))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(4).quality(1))
                                .with(EmptyEntry.builder().weight(30).quality(-3))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(-0.2f, 0f))
                                .with(ItemEntry.builder(Items.BONE).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.SAND).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(22).quality(-3))
                                .with(EmptyEntry.builder().weight(2))
                                .with(
                                        ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE)
                                        .quality(4)
                                )
                                .with(
                                        ItemEntry.builder(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(4)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                        .quality(2)
                                )
                );
    }
    private static LootTable.Builder endCityTreasureChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 6.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(20).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(40).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(45).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(6).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(6))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(3).quality(-1))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(3).quality(-1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SWORD)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_BOOTS)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_HELMET)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SHOVEL)
                                                .weight(9).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_SWORD)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_BOOTS)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_CHESTPLATE)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_LEGGINGS)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_HELMET)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_PICKAXE)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_SHOVEL)
                                                .weight(9).quality(1)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(20.0F, 39.0F)))
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(13).quality(-2))
                                .with(EmptyEntry.builder())
                                .with(ItemEntry.builder(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    private static LootTable.Builder iglooChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.APPLE).weight(30).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(30).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(20).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.STONE_AXE).weight(4))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(20).quality(-2))
                                .with(ItemEntry.builder(Items.EMERALD).quality(2))
                                .with(ItemEntry.builder(Items.EMERALD))
                                .with(ItemEntry.builder(Items.WHEAT).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE))
                );
    }
    private static LootTable.Builder jungleTempleChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 6.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(9).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(30).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(45).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.BAMBOO).weight(45).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(6).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BONE).weight(60).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(48).quality(-4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(9))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(3).quality(-1))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(3).quality(-1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3))
                                .with(ItemEntry.builder(Items.BOOK).weight(3).quality(1).apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(16).quality(-3))
                                .with(EmptyEntry.builder().weight(4))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).weight(3).quality(4))
                                .with(
                                        ItemEntry.builder(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE).weight(10).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                )
                );
    }
    private static LootTable.Builder netherBridgeChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(45).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(15).quality(-2))
                                .with(ItemEntry.builder(Items.NETHER_WART).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(30).quality(-1))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(24).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(15).quality(-2))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(13))
                                .with(ItemEntry.builder(Blocks.OBSIDIAN).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(14).quality(-2))
                                .with(EmptyEntry.builder().weight(1))
                                .with(ItemEntry.builder(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    private static LootTable.Builder pillagerOutpostChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0.0F, 1.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.CROSSBOW)))
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .with(ItemEntry.builder(Items.WHEAT).weight(7).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.POTATO).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.CARROT).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Blocks.DARK_OAK_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.23f))
                                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(21).quality(-2))
                                .with(ItemEntry.builder(Items.STRING).weight(12).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ARROW).weight(12).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.TRIPWIRE_HOOK).weight(9).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(9).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(3).quality(2).apply(EnchantRandomlyLootFunction.builder(registries)))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0.0F, 1.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.1f))
                                .with(ItemEntry.builder(Items.GOAT_HORN))
                                .apply(SetInstrumentLootFunction.builder(InstrumentTags.REGULAR_GOAT_HORNS))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(10).quality(-2))
                                .with(EmptyEntry.builder().weight(2))
                                .with(
                                        ItemEntry.builder(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE).weight(4).quality(4)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                )
                );
    }
    private static LootTable.Builder shipwreckMapChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.MAP)
                                                .apply(
                                                        ExplorationMapLootFunction.builder()
                                                                .withDestination(StructureTags.ON_TREASURE_MAPS)
                                                                .withDecoration(MapDecorationTypes.RED_X)
                                                                .withZoom((byte)1)
                                                                .withSkipExistingChunks(false)
                                                )
                                                .apply(SetNameLootFunction.builder(Text.translatable("filled_map.buried_treasure"), SetNameLootFunction.Target.ITEM_NAME))
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.COMPASS))
                                .with(ItemEntry.builder(Items.MAP))
                                .with(ItemEntry.builder(Items.CLOCK))
                                .with(ItemEntry.builder(Items.PAPER).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.FEATHER).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(13).quality(-2))
                                .with(EmptyEntry.builder().weight(2))
                                .with(ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).quality(4).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    private static LootTable.Builder shipwreckSupplyChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.PAPER).weight(24).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.POTATO).weight(21).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.MOSS_BLOCK).weight(21).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.POISONOUS_POTATO).weight(21).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.CARROT).weight(21).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(21).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 21.0F))))
                                .with(
                                        ItemEntry.builder(Items.SUSPICIOUS_STEW)
                                                .weight(30).quality(2)
                                                .apply(
                                                        SetStewEffectLootFunction.builder()
                                                                .withEffect(StatusEffects.NIGHT_VISION, UniformLootNumberProvider.create(7.0F, 10.0F))
                                                                .withEffect(StatusEffects.JUMP_BOOST, UniformLootNumberProvider.create(7.0F, 10.0F))
                                                                .withEffect(StatusEffects.WEAKNESS, UniformLootNumberProvider.create(6.0F, 8.0F))
                                                                .withEffect(StatusEffects.BLINDNESS, UniformLootNumberProvider.create(5.0F, 7.0F))
                                                                .withEffect(StatusEffects.POISON, UniformLootNumberProvider.create(10.0F, 20.0F))
                                                                .withEffect(StatusEffects.SATURATION, UniformLootNumberProvider.create(7.0F, 10.0F))
                                                )
                                )
                                .with(ItemEntry.builder(Items.COAL).weight(18).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 24.0F))))
                                .with(ItemEntry.builder(Blocks.PUMPKIN).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.BAMBOO).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(9).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.TNT).weight(3).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.LEATHER_HELMET).weight(9).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).weight(9).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.LEATHER_LEGGINGS).weight(9).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.LEATHER_BOOTS).weight(9).apply(EnchantRandomlyLootFunction.builder(registries)))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(13).quality(-2))
                                .with(EmptyEntry.builder().weight(2))
                                .with(ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).quality(4).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    private static LootTable.Builder shipwreckTreasureChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 6.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.6f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(90).quality(-4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(10).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(40).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(3).quality(5))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(2))
                                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(5))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(50).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(13).quality(-2))
                                .with(EmptyEntry.builder().weight(2))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).quality(4))
                                .with(ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    private static LootTable.Builder simpleDungeonChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(ItemEntry.builder(Items.SADDLE).weight(40).quality(-2))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(30).quality(3))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(4).quality(3))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(4).quality(2))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_13).weight(30).quality(-3))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(30).quality(-3))
                                .with(ItemEntry.builder(Items.NAME_TAG).weight(40).quality(-4))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(20).quality(-2))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(30).quality(-4))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(10))
                                .with(ItemEntry.builder(Items.BOOK).weight(20).quality(3).apply(EnchantRandomlyLootFunction.builder(registries)))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(30).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(60).quality(-6))
                                .with(ItemEntry.builder(Items.WHEAT).weight(60).quality(-6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BUCKET).weight(30).quality(-2))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(45).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(45).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.MELON_SEEDS).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(-0.3f, 0f))
                                .with(ItemEntry.builder(Items.BONE).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(20).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(20).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                );
    }
    private static LootTable.Builder strongholdCorridorChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.ENDER_PEARL).weight(30).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(6).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(30).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(45).quality(-6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(45).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_SWORD).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_CHESTPLATE).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_HELMET).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_LEGGINGS).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_BOOTS).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(3))
                                .with(ItemEntry.builder(Items.SADDLE).weight(3))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(3))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(3))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(3).quality(1))
                                .with(ItemEntry.builder(Items.BOOK).weight(2).quality(6).apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F))))
                                .with(ItemEntry.builder(Items.BOOK).apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(14).quality(-2))
                                .with(EmptyEntry.builder().weight(4))
                                .with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(2).quality(4))
                );
    }
    private static LootTable.Builder strongholdCrossingChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(30).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(15).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(45).quality(-4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(45).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(3))
                                .with(ItemEntry.builder(Items.BOOK).weight(2).quality(4).apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F))))
                                .with(ItemEntry.builder(Items.BOOK).apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F))))
                );
    }
    private static LootTable.Builder strongholdLibraryChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.25f))
                                .with(ItemEntry.builder(Items.BOOK).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.PAPER).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.MAP))
                                .with(ItemEntry.builder(Items.COMPASS))
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(7).quality(2)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F)))
                                )
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(3)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(30.0F)))
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
                );
    }
    private static LootTable.Builder underwaterRuinBigChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.11f, 0.18f))
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .apply(EnchantWithLevelsLootFunction.builder(registries, ConstantLootNumberProvider.create(25.0F)))
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.24f))
                                .with(ItemEntry.builder(Items.COAL).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2))
                                .with(ItemEntry.builder(Items.WHEAT).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(2).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).weight(10).quality(2).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).weight(2))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).weight(2))
                                .with(ItemEntry.builder(Items.FISHING_ROD).weight(10).quality(-1).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(
                                        ItemEntry.builder(Items.MAP)
                                                .weight(20).quality(1)
                                                .apply(
                                                        ExplorationMapLootFunction.builder()
                                                                .withDestination(StructureTags.ON_TREASURE_MAPS)
                                                                .withDecoration(MapDecorationTypes.RED_X)
                                                                .withZoom((byte)1)
                                                                .withSkipExistingChunks(false)
                                                )
                                                .apply(SetNameLootFunction.builder(Text.translatable("filled_map.buried_treasure"), SetNameLootFunction.Target.ITEM_NAME))
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0f))
                                .with(EmptyEntry.builder().weight(1))
                                .with(EmptyEntry.builder().weight(30).quality(-4))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).quality(4))
                );
    }
    private static LootTable.Builder underwaterRuinSmallChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.11f, 0.18f))
                                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder(registries)))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.24f))
                                .with(ItemEntry.builder(Items.COAL).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.STONE_AXE).weight(4))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(10).quality(-1))
                                .with(ItemEntry.builder(Items.EMERALD).quality(2))
                                .with(ItemEntry.builder(Items.EMERALD))
                                .with(ItemEntry.builder(Items.WHEAT).weight(20).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET))
                                .with(ItemEntry.builder(Items.FISHING_ROD).weight(5).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(
                                        ItemEntry.builder(Items.MAP)
                                                .weight(5).quality(2)
                                                .apply(
                                                        ExplorationMapLootFunction.builder()
                                                                .withDestination(StructureTags.ON_TREASURE_MAPS)
                                                                .withDecoration(MapDecorationTypes.RED_X)
                                                                .withZoom((byte)1)
                                                                .withSkipExistingChunks(false)
                                                )
                                                .apply(SetNameLootFunction.builder(Text.translatable("filled_map.buried_treasure"), SetNameLootFunction.Target.ITEM_NAME))
                                )
                );
    }
    private static LootTable.Builder villageWeaponsmithChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(9).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(30).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(45).quality(-6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(45).quality(-6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_SWORD).weight(15).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_CHESTPLATE).weight(15))
                                .with(ItemEntry.builder(Items.IRON_HELMET).weight(15))
                                .with(ItemEntry.builder(Items.IRON_LEGGINGS).weight(15))
                                .with(ItemEntry.builder(Items.IRON_BOOTS).weight(15))
                                .with(ItemEntry.builder(Blocks.OBSIDIAN).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.OAK_SAPLING).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(9).quality(-1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(3))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(3))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3))
                );
    }
    private static LootTable.Builder villageToolsmithChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(10))
                                .with(ItemEntry.builder(Items.COAL).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.STICK).weight(40).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_SHOVEL).weight(10))
                );
    }
    private static LootTable.Builder villageCartographerChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 7.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.MAP).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.PAPER).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.COMPASS).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.STICK).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
    private static LootTable.Builder villageMasonChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.STONE_BRICKS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))
                                .with(ItemEntry.builder(Items.STONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))
                                .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.SMOOTH_STONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.CLAY_BALL).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.FLOWER_POT).weight(10).quality(-1))
                                .with(ItemEntry.builder(Blocks.STONE).weight(20))
                                .with(ItemEntry.builder(Blocks.STONE_BRICKS).weight(20))
                                .with(ItemEntry.builder(Items.BREAD).weight(40).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.YELLOW_DYE).weight(10).quality(1))
                                .with(ItemEntry.builder(Blocks.SMOOTH_STONE).weight(10))
                                .with(ItemEntry.builder(Items.EMERALD).weight(10).quality(3))
                );
    }
    private static LootTable.Builder villageArmorerChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))
                                .with(ItemEntry.builder(Items.IRON_HELMET).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1f))))
                                .with(ItemEntry.builder(Items.BREAD).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(20).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(40).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.IRON_HELMET).weight(10).quality(1))
                                .with(ItemEntry.builder(Items.EMERALD).weight(10).quality(3))
                );
    }
    private static LootTable.Builder villageShepardChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.BROWN_WOOL).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.SHEARS).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1f))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Blocks.WHITE_WOOL).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.BLACK_WOOL).weight(15).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.GRAY_WOOL).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.BROWN_WOOL).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.LIGHT_GRAY_WOOL).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(5).quality(3))
                                .with(ItemEntry.builder(Items.SHEARS).weight(5))
                                .with(ItemEntry.builder(Items.WHEAT).weight(30).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                );
    }
    private static LootTable.Builder villageButcherChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.BEEF).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.COAL).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.PORKCHOP).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.EMERALD).weight(4).quality(3))
                                .with(ItemEntry.builder(Items.PORKCHOP).weight(24).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(24).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BEEF).weight(24).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.MUTTON).weight(24).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(12).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    private static LootTable.Builder villageFletcherChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.FLINT).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.ARROW).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.FEATHER).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.EMERALD).weight(4).quality(3))
                                .with(ItemEntry.builder(Items.ARROW).weight(8).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.FEATHER).weight(24).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EGG).weight(8).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.FLINT).weight(24).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.STICK).weight(24).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    private static LootTable.Builder villageFisherChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.SALMON).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.COD).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.COAL).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.EMERALD).weight(8).quality(3))
                                .with(ItemEntry.builder(Items.COD).weight(16).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.SALMON).weight(8).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WATER_BUCKET).weight(8).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BARREL).weight(8).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WHEAT_SEEDS).weight(24).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(16).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    private static LootTable.Builder villageTanneryChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.SADDLE).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.BREAD).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.LEATHER).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.15f))
                                .with(ItemEntry.builder(Items.LEATHER).weight(8).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).weight(16).quality(-2))
                                .with(ItemEntry.builder(Items.LEATHER_BOOTS).weight(16).quality(-2))
                                .with(ItemEntry.builder(Items.LEATHER_HELMET).weight(16).quality(-2))
                                .with(ItemEntry.builder(Items.BREAD).weight(40).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.LEATHER_LEGGINGS).weight(16).quality(-2))
                                .with(ItemEntry.builder(Items.SADDLE).weight(8))
                                .with(ItemEntry.builder(Items.EMERALD).weight(8).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
    }
    private static LootTable.Builder villageTempleChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0.1f, 0.18f))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(12).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(42).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(42).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(6).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
    }
    private static LootTable.Builder villagePlainsChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.DANDELION).weight(2).quality(-2))
                                .with(ItemEntry.builder(Items.POPPY).weight(1))
                                .with(ItemEntry.builder(Items.POTATO).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(1).quality(1))
                                .with(ItemEntry.builder(Items.FEATHER).weight(1).quality(-2))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.OAK_SAPLING).weight(5).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
    private static LootTable.Builder villageTaigaHouseChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.FERN).weight(2).quality(-2))
                                .with(ItemEntry.builder(Items.LARGE_FERN).weight(2).quality(-2))
                                .with(ItemEntry.builder(Items.POTATO).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SWEET_BERRIES).weight(5).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(5).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_PIE).weight(1).quality(1))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.SPRUCE_SAPLING).weight(5).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.SPRUCE_SIGN).weight(1))
                                .with(ItemEntry.builder(Items.SPRUCE_LOG).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                );
    }
    private static LootTable.Builder villageSavannaHouseChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(1).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.SHORT_GRASS).weight(5).quality(-2))
                                .with(ItemEntry.builder(Items.TALL_GRASS).weight(5).quality(-2))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.WHEAT_SEEDS).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.ACACIA_SAPLING).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(1))
                                .with(ItemEntry.builder(Blocks.TORCH).weight(1).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.BUCKET).weight(1).quality(-1))
                );
    }
    private static LootTable.Builder villageSnowyHouseChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Blocks.BLUE_ICE).weight(1).quality(-1))
                                .with(ItemEntry.builder(Blocks.SNOW_BLOCK).weight(4))
                                .with(ItemEntry.builder(Items.POTATO).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SOUP).weight(1))
                                .with(ItemEntry.builder(Items.FURNACE).weight(1))
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.SNOWBALL).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(5).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
    }
    private static LootTable.Builder villageDesertHouseChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.CLAY_BALL).weight(1))
                                .with(ItemEntry.builder(Items.GREEN_DYE).weight(1).quality(-1))
                                .with(ItemEntry.builder(Blocks.CACTUS).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(-1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(1).quality(1))
                                .with(ItemEntry.builder(Blocks.DEAD_BUSH).weight(2).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    public static LootTable.Builder woodlandMansionChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(ItemEntry.builder(Items.LEAD).weight(20).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(15).quality(3))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2).quality(5))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_13).weight(15).quality(0))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(15).quality(0))
                                .with(ItemEntry.builder(Items.NAME_TAG).weight(20).quality(1))
                                .with(ItemEntry.builder(Items.CHAINMAIL_CHESTPLATE).weight(10).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND_HOE).weight(15).quality(2))
                                .with(ItemEntry.builder(Items.DIAMOND_CHESTPLATE).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).weight(10).quality(2).apply(EnchantRandomlyLootFunction.builder(registries)))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(20).quality(1))
                                .with(ItemEntry.builder(Items.WHEAT).weight(20).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BUCKET).weight(10).quality(1))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.MELON_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .with(ItemEntry.builder(Items.BONE).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(1).quality(0))
                                .with(ItemEntry.builder(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(3))
                );
    }
    private static LootTable.Builder ruinedPortalChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(4.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.5f))
                                .with(ItemEntry.builder(Items.OBSIDIAN).weight(40).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.FLINT).weight(40).quality(-6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(40).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(9.0F, 18.0F))))
                                .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(40).quality(-4))
                                .with(ItemEntry.builder(Items.FIRE_CHARGE).weight(40).quality(-4))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(15).quality(1))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(15).quality(-2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 24.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).weight(15).quality(1).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_AXE).weight(15).quality(1).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_HOE).weight(15).quality(-1).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_SHOVEL).weight(15).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_PICKAXE).weight(15).quality(1).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_BOOTS).weight(15).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).weight(15).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).weight(15).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GOLDEN_LEGGINGS).weight(15).apply(EnchantRandomlyLootFunction.builder(registries)))
                                .with(ItemEntry.builder(Items.GLISTERING_MELON_SLICE).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(5))
                                .with(ItemEntry.builder(Items.LIGHT_WEIGHTED_PRESSURE_PLATE).weight(5).quality(-2))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.CLOCK).weight(5))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.BELL).weight(1))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).quality(4))
                                .with(ItemEntry.builder(Items.GOLD_BLOCK).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
    public static LootTable.Builder trialChambersChamberDispenser(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.WATER_BUCKET).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(40).quality(1))
                                .with(ItemEntry.builder(Items.ARROW).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))).weight(40).quality(-3))
                                .with(ItemEntry.builder(Items.SNOWBALL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))).weight(60).quality(3))
                                .with(ItemEntry.builder(Items.EGG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))).weight(20).quality(3))
                                .with(ItemEntry.builder(Items.FIRE_CHARGE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))).weight(60).quality(-3))
                                .with(
                                        ItemEntry.builder(Items.SPLASH_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.SLOWNESS))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(-2)
                                )
                                .with(
                                        ItemEntry.builder(Items.SPLASH_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.POISON))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(-2)
                                )
                                .with(
                                        ItemEntry.builder(Items.SPLASH_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.WEAKNESS))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(-2)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.SLOWNESS))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(-2)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.POISON))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(-2)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.WEAKNESS))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(-2)
                                )
                                .with(
                                        ItemEntry.builder(Items.LINGERING_POTION)
                                                .apply(SetPotionLootFunction.builder(Potions.HEALING))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                                .weight(10).quality(3)
                                )
                );
    }
    public static LootTable.Builder trialChambersCorridorPot(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.EMERALD).quality(-5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).weight(125))
                                .with(ItemEntry.builder(Items.ARROW).quality(-6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))).weight(100))
                                .with(ItemEntry.builder(Items.IRON_INGOT).quality(-3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(100))
                                .with(ItemEntry.builder(Items.TRIAL_KEY).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(10))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CREATOR_MUSIC_BOX).quality(2).weight(5))
                                .with(ItemEntry.builder(Items.DIAMOND).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(5))
                                .with(ItemEntry.builder(Items.EMERALD_BLOCK).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(5))
                                .with(ItemEntry.builder(Items.DIAMOND_BLOCK).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(1))
                );
    }
    public static LootTable.Builder trialChambersSupplyChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 5.0F))
                                .with(ItemEntry.builder(Items.ARROW).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 14.0F))).weight(2))
                                .with(
                                        ItemEntry.builder(Items.TIPPED_ARROW)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.POISON))
                                                .weight(1).quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.TIPPED_ARROW)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.SLOWNESS))
                                                .weight(1).quality(2)
                                )
                                .with(ItemEntry.builder(Items.BAKED_POTATO).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))).weight(2))
                                .with(ItemEntry.builder(Items.GLOW_BERRIES).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 10.0F))).weight(2))
                                .with(ItemEntry.builder(Items.ACACIA_PLANKS).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))).weight(1))
                                .with(ItemEntry.builder(Items.MOSS_BLOCK).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))).weight(1))
                                .with(ItemEntry.builder(Items.BONE_MEAL).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))).weight(1))
                                .with(ItemEntry.builder(Items.TUFF).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 10.0F))).weight(1))
                                .with(ItemEntry.builder(Items.TORCH).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))).weight(1))
                                .with(
                                        ItemEntry.builder(Items.POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.REGENERATION))
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.POTION)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRENGTH))
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.STONE_PICKAXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(2)
                                                .quality(0)
                                )
                                .with(ItemEntry.builder(Items.MILK_BUCKET).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                );
    }
    public static LootTable.Builder trialChambersEntranceChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .with(ItemEntry.builder(Items.TRIAL_KEY).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(1))
                                .with(ItemEntry.builder(Items.STICK).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))).weight(5))
                                .with(ItemEntry.builder(Items.WOODEN_AXE).quality(0).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(10))
                                .with(ItemEntry.builder(Items.HONEYCOMB).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))).weight(10))
                                .with(ItemEntry.builder(Items.ARROW).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 10.0F))).weight(10))
                );
    }
    public static LootTable.Builder trialChambersIntersectionChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(ItemEntry.builder(Items.DIAMOND_BLOCK).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).weight(1))
                                .with(ItemEntry.builder(Items.EMERALD_BLOCK).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).weight(5))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.5F)))
                                                .weight(5).quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE).quality(2)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.5F)))
                                                .weight(5)
                                )
                                .with(ItemEntry.builder(Items.DIAMOND).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(10))
                                .with(ItemEntry.builder(Items.CAKE).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))).weight(20))
                                .with(ItemEntry.builder(Items.AMETHYST_SHARD).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 20.0F))).weight(20))
                                .with(ItemEntry.builder(Items.IRON_BLOCK).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(20))
                );
    }
    public static LootTable.Builder trialChambersIntersectionBarrelChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.4F, 0.9F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                                .weight(1).quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(1).quality(2)
                                )
                                .with(ItemEntry.builder(Items.DIAMOND).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).weight(1))
                                .with(
                                        ItemEntry.builder(Items.COMPASS)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(1).quality(1)
                                )
                                .with(ItemEntry.builder(Items.BUCKET).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(1))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(4).quality(1)
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_PICKAXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(4).quality(1)
                                )
                                .with(ItemEntry.builder(Items.BAMBOO_PLANKS).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 15.0F))).weight(10))
                                .with(ItemEntry.builder(Items.BAKED_POTATO).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 10.0F))).weight(10))
                );
    }
    public static LootTable.Builder trialChambersCorridorChest(RegistryWrapper.WrapperLookup registries) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(
                                        ItemEntry.builder(Items.IRON_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.4F, 0.9F)))
                                                .apply(EnchantRandomlyLootFunction.builder(registries))
                                                .weight(1).quality(2)
                                )
                                .with(ItemEntry.builder(Items.HONEYCOMB).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))).weight(1))
                                .with(
                                        ItemEntry.builder(Items.STONE_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(2).quality(0)
                                )
                                .with(
                                        ItemEntry.builder(Items.STONE_PICKAXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .weight(2).quality(0)
                                )
                                .with(ItemEntry.builder(Items.ENDER_PEARL).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(2))
                                .with(ItemEntry.builder(Items.BAMBOO_HANGING_SIGN).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))).weight(2))
                                .with(ItemEntry.builder(Items.BAMBOO_PLANKS).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))).weight(2))
                                .with(ItemEntry.builder(Items.SCAFFOLDING).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 10.0F))).weight(2))
                                .with(ItemEntry.builder(Items.TORCH).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))).weight(2))
                                .with(ItemEntry.builder(Items.TUFF).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 20.0F))).weight(3))
                );
    }
}
