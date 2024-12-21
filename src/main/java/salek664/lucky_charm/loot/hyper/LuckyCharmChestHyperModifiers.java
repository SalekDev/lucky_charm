package salek664.lucky_charm.loot.hyper;

import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.block.Blocks;
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
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.text.Text;
import salek664.lucky_charm.item.LuckyCharmItems;

import java.util.List;
import java.util.Optional;

public class LuckyCharmChestHyperModifiers {
    public static Optional<LootTable> attemptReplace(RegistryKey<LootTable> key, LootTableSource source, boolean enabled, List<String> excepts) {
        String k = key.getValue().toUnderscoreSeparatedString();
        for (String except : excepts) {
            if (k.endsWith(except)) {
                enabled = !enabled;
                break;
            }
        }
        if (enabled && source.isBuiltin()) {
            if (LootTables.ABANDONED_MINESHAFT_CHEST == key) {
                return Optional.of(abandonedMineshaftChest().build());
            } else if (LootTables.BASTION_BRIDGE_CHEST == key) {
                return Optional.of(bastionBridgeChest().build());
            } else if (LootTables.BASTION_HOGLIN_STABLE_CHEST == key) {
                return Optional.of(bastionHoglinStableChest().build());
            } else if (LootTables.BASTION_OTHER_CHEST == key) {
                return Optional.of(bastionOtherChest().build());
            } else if (LootTables.BASTION_TREASURE_CHEST == key) {
                return Optional.of(bastionTreasureChest().build());
            } else if (LootTables.BURIED_TREASURE_CHEST == key) {
                return Optional.of(buriedTreasureChest().build());
            } else if (LootTables.ANCIENT_CITY_CHEST == key) {
                return Optional.of(ancientCityChest().build());
            } else if (LootTables.ANCIENT_CITY_ICE_BOX_CHEST == key) {
                return Optional.of(ancientCityIceboxChest().build());
            } else if (LootTables.DESERT_PYRAMID_CHEST == key) {
                return Optional.of(desertPyramidChest().build());
            } else if (LootTables.END_CITY_TREASURE_CHEST == key) {
                return Optional.of(endCityTreasureChest().build());
            } else if (LootTables.IGLOO_CHEST_CHEST == key) {
                return Optional.of(iglooChest().build());
            } else if (LootTables.JUNGLE_TEMPLE_CHEST == key) {
                return Optional.of(jungleTempleChest().build());
            } else if (LootTables.NETHER_BRIDGE_CHEST == key) {
                return Optional.of(netherBridgeChest().build());
            } else if (LootTables.PILLAGER_OUTPOST_CHEST == key) {
                return Optional.of(pillagerOutpostChest().build());
            } else if (LootTables.SHIPWRECK_MAP_CHEST == key) {
                return Optional.of(shipwreckMapChest().build());
            } else if (LootTables.SHIPWRECK_SUPPLY_CHEST == key) {
                return Optional.of(shipwreckSupplyChest().build());
            } else if (LootTables.SHIPWRECK_TREASURE_CHEST == key) {
                return Optional.of(shipwreckTreasureChest().build());
            } else if (LootTables.SIMPLE_DUNGEON_CHEST == key) {
                return Optional.of(simpleDungeonChest().build());
            } else if (LootTables.STRONGHOLD_CORRIDOR_CHEST == key) {
                return Optional.of(strongholdCorridorChest().build());
            } else if (LootTables.STRONGHOLD_CROSSING_CHEST == key) {
                return Optional.of(strongholdCrossingChest().build());
            } else if (LootTables.STRONGHOLD_LIBRARY_CHEST == key) {
                return Optional.of(strongholdLibraryChest().build());
            } else if (LootTables.UNDERWATER_RUIN_BIG_CHEST == key) {
                return Optional.of(underwaterRuinBigChest().build());
            } else if (LootTables.UNDERWATER_RUIN_SMALL_CHEST == key) {
                return Optional.of(underwaterRuinSmallChest().build());
            } else if (LootTables.VILLAGE_WEAPONSMITH_CHEST == key) {
                return Optional.of(villageWeaponsmithChest().build());
            } else if (LootTables.VILLAGE_TOOLSMITH_CHEST == key) {
                return Optional.of(villageToolsmithChest().build());
            } else if (LootTables.VILLAGE_CARTOGRAPHER_CHEST == key) {
                return Optional.of(villageCartographerChest().build());
            } else if (LootTables.VILLAGE_MASON_CHEST == key) {
                return Optional.of(villageMasonChest().build());
            } else if (LootTables.VILLAGE_ARMORER_CHEST == key) {
                return Optional.of(villageArmorerChest().build());
            } else if (LootTables.VILLAGE_SHEPARD_CHEST == key) {
                return Optional.of(villageShepardChest().build());
            } else if (LootTables.VILLAGE_BUTCHER_CHEST == key) {
                return Optional.of(villageButcherChest().build());
            } else if (LootTables.VILLAGE_FLETCHER_CHEST == key) {
                return Optional.of(villageFletcherChest().build());
            } else if (LootTables.VILLAGE_FISHER_CHEST == key) {
                return Optional.of(villageFisherChest().build());
            } else if (LootTables.VILLAGE_TANNERY_CHEST == key) {
                return Optional.of(villageTanneryChest().build());
            } else if (LootTables.VILLAGE_TEMPLE_CHEST == key) {
                return Optional.of(villageTempleChest().build());
            } else if (LootTables.VILLAGE_PLAINS_CHEST == key) {
                return Optional.of(villagePlainsChest().build());
            } else if (LootTables.VILLAGE_TAIGA_HOUSE_CHEST == key) {
                return Optional.of(villageTaigaHouseChest().build());
            } else if (LootTables.VILLAGE_SAVANNA_HOUSE_CHEST == key) {
                return Optional.of(villageSavannaHouseChest().build());
            } else if (LootTables.VILLAGE_SNOWY_HOUSE_CHEST == key) {
                return Optional.of(villageSnowyHouseChest().build());
            } else if (LootTables.VILLAGE_DESERT_HOUSE_CHEST == key) {
                return Optional.of(villageDesertHouseChest().build());
            } else if (LootTables.RUINED_PORTAL_CHEST == key) {
                return Optional.of(ruinedPortalChest().build());
            }
        }
        return Optional.empty();
    }
    public static LootTable.Builder abandonedMineshaftChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(20).quality(4))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).quality(8))
                                .with(ItemEntry.builder(Items.NAME_TAG).weight(30).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).weight(10).quality(6).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(5).quality(1))
                                .with(EmptyEntry.builder().weight(5).quality(0))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(3).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GLOW_BERRIES).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.MELON_SEEDS).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .with(ItemEntry.builder(Blocks.RAIL).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.POWERED_RAIL).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.DETECTOR_RAIL).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.ACTIVATOR_RAIL).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.TORCH).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 16.0F))))
                );
    }
    public static LootTable.Builder bastionBridgeChest() {
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
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.5F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                                .with(ItemEntry.builder(Items.SPECTRAL_ARROW).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(10.0F, 28.0F))))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 12.0F))))
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).quality(6).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_CHESTPLATE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_HELMET)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_LEGGINGS)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_BOOTS)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.STRING).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.LEATHER).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.ARROW).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).quality(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(11).quality(0))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9).quality(0))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    public static LootTable.Builder bastionHoglinStableChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SHOVEL)
                                                .weight(15).quality(4)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.8F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .weight(12).quality(4)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.95F)))
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                )
                                .with(ItemEntry.builder(Items.NETHERITE_SCRAP).quality(6).weight(8).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.ANCIENT_DEBRIS).quality(6).weight(12).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.ANCIENT_DEBRIS).quality(6).weight(5).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(12).quality(0).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).weight(16).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(10).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(3)
                                )
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.GLOWSTONE).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.SOUL_SAND).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.CRIMSON_NYLIUM).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.LEATHER).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.ARROW).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.STRING).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.PORKCHOP).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.COOKED_PORKCHOP).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.CRIMSON_FUNGUS).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.CRIMSON_ROOTS).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(11).quality(0))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9).quality(0))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    public static LootTable.Builder bastionOtherChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .weight(6).quality(4)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                )
                                .with(ItemEntry.builder(Items.DIAMOND_SHOVEL).quality(4).weight(6).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.CROSSBOW)
                                                .weight(6).quality(3)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.9F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                )
                                .with(ItemEntry.builder(Items.ANCIENT_DEBRIS).weight(12).quality(6).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.NETHERITE_SCRAP).weight(4).quality(6).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.SPECTRAL_ARROW).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(10.0F, 22.0F))))
                                .with(ItemEntry.builder(Items.PIGLIN_BANNER_PATTERN).weight(9).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_PIGSTEP).weight(5).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(12).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(9).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(10).quality(2).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.SOUL_SPEED)))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(2.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(
                                        ItemEntry.builder(Items.IRON_SWORD)
                                                .weight(2).quality(3)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1F, 0.9F)))
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                )
                                .with(ItemEntry.builder(Blocks.IRON_BLOCK).weight(2).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_BOOTS)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.SOUL_SPEED))
                                                .quality(2)
                                )
                                .with(
                                        ItemEntry.builder(Items.GOLDEN_AXE)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(2)
                                )
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).quality(4).weight(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.CROSSBOW).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(3).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_LEGGINGS).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_BOOTS).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 4.0F))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).weight(2).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.CHAIN).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.MAGMA_CREAM).quality(1).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Blocks.BONE_BLOCK).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.OBSIDIAN).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ARROW).quality(1).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 17.0F))))
                                .with(ItemEntry.builder(Items.COOKED_PORKCHOP).quality(0).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(11).quality(0))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9).quality(0))
                                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    public static LootTable.Builder bastionTreasureChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.17f))
                                .with(ItemEntry.builder(Items.NETHERITE_INGOT).weight(15).quality(8).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.ANCIENT_DEBRIS).weight(10).quality(5).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.NETHERITE_SCRAP).weight(8).quality(5).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Blocks.ANCIENT_DEBRIS).weight(4).quality(5).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SWORD)
                                                .weight(6)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(5)
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                                .weight(6)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(5)
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_HELMET)
                                                .weight(6)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(5)
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                                .weight(6)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(5)
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_BOOTS)
                                                .weight(6)
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantRandomlyLootFunction.builder())
                                                .quality(5)
                                )
                                .with(ItemEntry.builder(Items.DIAMOND_SWORD).weight(6).quality(2))
                                .with(ItemEntry.builder(Items.DIAMOND_CHESTPLATE).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.DIAMOND_HELMET).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.DIAMOND_BOOTS).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.DIAMOND_LEGGINGS).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(5).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2).quality(8).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.SPECTRAL_ARROW).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(12.0F, 25.0F))))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.IRON_BLOCK).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 9.0F))))
                                .with(ItemEntry.builder(Blocks.CRYING_OBSIDIAN).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.QUARTZ).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 23.0F))))
                                .with(ItemEntry.builder(Blocks.GILDED_BLACKSTONE).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.MAGMA_CREAM).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(11).quality(0))
                                .with(ItemEntry.builder(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                )
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).weight(1))
        );
    }
    public static LootTable.Builder buriedTreasureChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.HEART_OF_THE_SEA)))
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(5.0F, 8.0F))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(10).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.TNT).weight(5).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.EMERALD).weight(5).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(5).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.PRISMARINE_CRYSTALS).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0.0F, 1.0F))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).quality(0))
                                .with(ItemEntry.builder(Items.IRON_SWORD).quality(2))
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
    public static LootTable.Builder ancientCityChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(5.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.5f))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(1).quality(2))
                                .with(ItemEntry.builder(Items.COMPASS).weight(2).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.SCULK_CATALYST).weight(2).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.NAME_TAG).quality(3).weight(2))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_HOE)
                                                .weight(2).quality(3)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F)))
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(30.0F, 50.0F)).allowTreasureEnchantments())
                                )
                                .with(ItemEntry.builder(Items.LEAD).weight(2).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(2).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(2).quality(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_13).quality(2).weight(2))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).quality(2).weight(2))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                                .weight(2).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(30.0F, 50.0F)).allowTreasureEnchantments())
                                )
                                .with(ItemEntry.builder(Items.BOOK).quality(3).weight(3).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.SWIFT_SNEAK)))
                                .with(ItemEntry.builder(Items.SCULK).weight(3).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.SCULK_SENSOR).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.CANDLE).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.AMETHYST_SHARD).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(3).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GLOW_BERRIES).weight(3).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(
                                        ItemEntry.builder(Items.IRON_LEGGINGS)
                                                .weight(3).quality(3)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(ItemEntry.builder(Items.ECHO_SHARD).weight(4).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.DISC_FRAGMENT_5).weight(4).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(
                                        ItemEntry.builder(Items.POTION)
                                                .weight(5).quality(2)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                                                .apply(SetPotionLootFunction.builder(Potions.STRONG_REGENERATION))
                                )
                                .with(ItemEntry.builder(Items.BOOK).weight(5).quality(4).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.BOOK).weight(5).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.BONE).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.SOUL_TORCH).weight(5).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 15.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(7).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 15.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(75).quality(0))
                                .with(ItemEntry.builder(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE).weight(4).quality(3))
                                .with(ItemEntry.builder(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(5))
                );
    }
    public static LootTable.Builder ancientCityIceboxChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(4.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(
                                        ItemEntry.builder(Items.SUSPICIOUS_STEW)
                                                .weight(1).quality(2)
                                                .apply(
                                                        SetStewEffectLootFunction.builder()
                                                                .withEffect(StatusEffects.NIGHT_VISION, UniformLootNumberProvider.create(7.0F, 10.0F))
                                                                .withEffect(StatusEffects.BLINDNESS, UniformLootNumberProvider.create(5.0F, 7.0F))
                                                )
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F)))
                                )
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(1).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.BAKED_POTATO).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.PACKED_ICE).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.SNOWBALL).weight(4).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                );
    }
    public static LootTable.Builder desertPyramidChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(5).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(15).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BONE).weight(25).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.SPIDER_EYE).weight(25).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(25).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(20).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(15).quality(1))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(10).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).weight(20).quality(4).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(20).quality(3))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2).quality(4))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNE_GEM).weight(5).quality(4))
                                .with(EmptyEntry.builder().weight(15).quality(0))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(-0.2f, 0f))
                                .with(ItemEntry.builder(Items.BONE).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.SAND).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(30).quality(0))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).quality(5))
                                .with(ItemEntry.builder(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(5).quality(3).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    public static LootTable.Builder endCityTreasureChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 6.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0.27F, 0.6F))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(3).quality(0))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).quality(0))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).quality(1))
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SWORD)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_BOOTS)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_HELMET)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.DIAMOND_SHOVEL)
                                                .weight(3).quality(5)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_SWORD)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_BOOTS)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_CHESTPLATE)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_LEGGINGS)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_HELMET)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_PICKAXE)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                                .with(
                                        ItemEntry.builder(Items.IRON_SHOVEL)
                                                .weight(3).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(14).quality(0))
                                .with(ItemEntry.builder(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    public static LootTable.Builder iglooChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.APPLE).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.STONE_AXE).weight(2).quality(0))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(10).quality(0))
                                .with(ItemEntry.builder(Items.EMERALD).quality(3))
                                .with(ItemEntry.builder(Items.WHEAT).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.GOLDEN_APPLE))
                );
    }
    public static LootTable.Builder jungleTempleChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 6.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(3).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.BAMBOO).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BONE).weight(20).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(16).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(3).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).quality(1))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).quality(4).apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0F)).allowTreasureEnchantments()))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(8).quality(0))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).quality(4))
                                .with(ItemEntry.builder(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE).weight(4).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    public static LootTable.Builder netherBridgeChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(5).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(5).quality(0))
                                .with(ItemEntry.builder(Items.NETHER_WART).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(10).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(8).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3).quality(2))
                                .with(ItemEntry.builder(Blocks.OBSIDIAN).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(14).quality(0))
                                .with(ItemEntry.builder(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    public static LootTable.Builder pillagerOutpostChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(0.0F, 1.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.1f))
                                .with(ItemEntry.builder(Items.CROSSBOW))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.WHEAT).weight(7).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.POTATO).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.CARROT).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(ItemEntry.builder(Blocks.DARK_OAK_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.23f))
                                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(7).quality(3))
                                .with(ItemEntry.builder(Items.STRING).weight(4).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.ARROW).weight(4).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.TRIPWIRE_HOOK).weight(3).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(3).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(1).quality(2).apply(EnchantRandomlyLootFunction.builder()))
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
                                .with(EmptyEntry.builder().weight(3).quality(0))
                                .with(ItemEntry.builder(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    public static LootTable.Builder shipwreckMapChest() {
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
                                .with(ItemEntry.builder(Items.COMPASS).quality(2))
                                .with(ItemEntry.builder(Items.MAP).quality(1))
                                .with(ItemEntry.builder(Items.CLOCK).quality(2))
                                .with(ItemEntry.builder(Items.PAPER).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.FEATHER).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(5).quality(0))
                                .with(ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    public static LootTable.Builder shipwreckSupplyChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.PAPER).weight(8).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.POTATO).weight(7).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.MOSS_BLOCK).weight(7).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.POISONOUS_POTATO).weight(7).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                .with(ItemEntry.builder(Items.CARROT).weight(7).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(7).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 21.0F))))
                                .with(
                                        ItemEntry.builder(Items.SUSPICIOUS_STEW)
                                                .weight(10).quality(2)
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
                                .with(ItemEntry.builder(Items.COAL).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 24.0F))))
                                .with(ItemEntry.builder(Blocks.PUMPKIN).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.BAMBOO).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(3).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Blocks.TNT).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.LEATHER_HELMET).weight(3).quality(1).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).weight(3).quality(1).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.LEATHER_LEGGINGS).weight(3).quality(1).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.LEATHER_BOOTS).weight(3).quality(1).apply(EnchantRandomlyLootFunction.builder()))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(5).quality(0))
                                .with(ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    public static LootTable.Builder shipwreckTreasureChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 6.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(90).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(10).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(40).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(5).quality(4))
                                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(5).quality(3))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNE_GEM).quality(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 5.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.2f))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(50).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(20).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 10.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(25).quality(0))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).quality(4))
                                .with(ItemEntry.builder(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).weight(5).quality(2).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
                );
    }
    public static LootTable.Builder simpleDungeonChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))
                                .with(ItemEntry.builder(Items.SADDLE).weight(20).quality(1))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(15).quality(4))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2).quality(5))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(2).quality(3))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_13).weight(15).quality(2))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(15).quality(2))
                                .with(ItemEntry.builder(Items.NAME_TAG).weight(20).quality(2))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(10).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(15).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).weight(10).quality(4).apply(EnchantRandomlyLootFunction.builder()))
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BREAD).quality(0).weight(20))
                                .with(ItemEntry.builder(Items.WHEAT).weight(20).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BUCKET).weight(10).quality(1))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.MELON_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(-0.3f, 0f))
                                .with(ItemEntry.builder(Items.BONE).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.GUNPOWDER).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.STRING).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                );
    }
    public static LootTable.Builder strongholdCorridorChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.ENDER_PEARL).weight(10).quality(3))
                                .with(ItemEntry.builder(Items.DIAMOND).weight(3).quality(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.IRON_SWORD).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.IRON_CHESTPLATE).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HELMET).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.IRON_LEGGINGS).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.IRON_BOOTS).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).quality(3))
                                .with(ItemEntry.builder(Items.SADDLE).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).quality(0))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).quality(1))
                                .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).quality(2))
                                .with(ItemEntry.builder(Items.BOOK).quality(3).apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0F)).allowTreasureEnchantments()))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9).quality(0))
                                .with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1).quality(4))
                );
    }
    public static LootTable.Builder strongholdCrossingChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.REDSTONE).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 9.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).quality(1))
                                .with(ItemEntry.builder(Items.BOOK).quality(3).apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0F)).allowTreasureEnchantments()))
                );
    }
    public static LootTable.Builder strongholdLibraryChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 10.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.25f))
                                .with(ItemEntry.builder(Items.BOOK).weight(20).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.PAPER).weight(20).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.MAP).quality(0))
                                .with(ItemEntry.builder(Items.COMPASS).quality(1))
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .weight(10).quality(4)
                                                .apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0F)).allowTreasureEnchantments())
                                )
                )
                .pool(
                        LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
                );
    }
    public static LootTable.Builder underwaterRuinBigChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.25f))
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(25.0F)).allowTreasureEnchantments())
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.24f))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNE_GEM).quality(5))
                                .with(ItemEntry.builder(Items.COAL).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(10).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).quality(5))
                                .with(ItemEntry.builder(Items.WHEAT).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).quality(3))
                                .with(ItemEntry.builder(Items.BOOK).weight(5).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).quality(1))
                                .with(ItemEntry.builder(Items.FISHING_ROD).weight(5).quality(1).apply(EnchantRandomlyLootFunction.builder()))
                                .with(
                                        ItemEntry.builder(Items.MAP)
                                                .weight(10).quality(3)
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
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(31).quality(0))
                                .with(ItemEntry.builder(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE).quality(3))
                );
    }
    public static LootTable.Builder underwaterRuinSmallChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(0f))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.25f))
                                .with(
                                        ItemEntry.builder(Items.BOOK)
                                                .apply(EnchantRandomlyLootFunction.builder())
                                )
                )
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.24f))
                                .with(ItemEntry.builder(Items.COAL).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.STONE_AXE).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(5).quality(0))
                                .with(ItemEntry.builder(Items.EMERALD).quality(5))
                                .with(ItemEntry.builder(Items.WHEAT).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
                )
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).quality(1))
                                .with(ItemEntry.builder(Items.FISHING_ROD).weight(5).quality(1).apply(EnchantRandomlyLootFunction.builder()))
                                .with(
                                        ItemEntry.builder(Items.MAP)
                                                .weight(5).quality(3)
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
    public static LootTable.Builder villageWeaponsmithChest() {
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
                                .with(ItemEntry.builder(Items.DIAMOND).weight(3).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.IRON_SWORD).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.IRON_CHESTPLATE).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.IRON_HELMET).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.IRON_LEGGINGS).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.IRON_BOOTS).weight(5).quality(2))
                                .with(ItemEntry.builder(Blocks.OBSIDIAN).weight(5).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Blocks.OAK_SAPLING).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(3).quality(1))
                                .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).quality(1))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).quality(1))
                                .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).quality(2))
                );
    }
    public static LootTable.Builder villageToolsmithChest() {
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
                                .with(ItemEntry.builder(Items.DIAMOND).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_PICKAXE).weight(2))
                                .with(ItemEntry.builder(Items.COAL).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.STICK).weight(20).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.IRON_SHOVEL).weight(5).quality(2))
                );
    }
    public static LootTable.Builder villageCartographerChest() {
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
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.51f))
                                .with(ItemEntry.builder(Items.MAP).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.PAPER).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.COMPASS).weight(5).quality(2))
                                .with(ItemEntry.builder(Items.BREAD).weight(15).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.STICK).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
    public static LootTable.Builder villageMasonChest() {
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
                                .with(ItemEntry.builder(Items.CLAY_BALL).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.FLOWER_POT).quality(0))
                                .with(ItemEntry.builder(Blocks.STONE).weight(2).quality(1))
                                .with(ItemEntry.builder(Blocks.STONE_BRICKS).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.BREAD).weight(4).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.YELLOW_DYE).quality(1))
                                .with(ItemEntry.builder(Blocks.SMOOTH_STONE).quality(1))
                                .with(ItemEntry.builder(Items.EMERALD).quality(3))
                );
    }
    public static LootTable.Builder villageArmorerChest() {
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
                                .with(ItemEntry.builder(Items.IRON_INGOT).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(4).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.IRON_HELMET).quality(2))
                                .with(ItemEntry.builder(Items.EMERALD).quality(3))
                );
    }
    public static LootTable.Builder villageShepardChest() {
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
                                .with(ItemEntry.builder(Blocks.WHITE_WOOL).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                                .with(ItemEntry.builder(Blocks.BLACK_WOOL).weight(3).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.GRAY_WOOL).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.BROWN_WOOL).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Blocks.LIGHT_GRAY_WOOL).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).quality(3))
                                .with(ItemEntry.builder(Items.SHEARS).quality(1))
                                .with(ItemEntry.builder(Items.WHEAT).weight(6).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 6.0F))))
                );
    }
    public static LootTable.Builder villageButcherChest() {
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
                                .with(ItemEntry.builder(Items.EMERALD).quality(3))
                                .with(ItemEntry.builder(Items.PORKCHOP).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(6).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BEEF).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.MUTTON).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    public static LootTable.Builder villageFletcherChest() {
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
                                .with(ItemEntry.builder(Items.EMERALD).quality(3))
                                .with(ItemEntry.builder(Items.ARROW).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.FEATHER).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EGG).weight(2).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.FLINT).weight(6).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.STICK).weight(6).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    public static LootTable.Builder villageFisherChest() {
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
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(3))
                                .with(ItemEntry.builder(Items.COD).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.SALMON).weight(1).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WATER_BUCKET).weight(1).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.BARREL).weight(1).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.WHEAT_SEEDS).weight(3).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(2).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    public static LootTable.Builder villageTanneryChest() {
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
                                .with(ItemEntry.builder(Items.LEATHER).weight(1).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.LEATHER_CHESTPLATE).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.LEATHER_BOOTS).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.LEATHER_HELMET).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.BREAD).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.LEATHER_LEGGINGS).weight(2).quality(1))
                                .with(ItemEntry.builder(Items.SADDLE).weight(1).quality(0))
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
    }
    public static LootTable.Builder villageTempleChest() {
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
                                .with(ItemEntry.builder(Items.REDSTONE).weight(2).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(7).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(7).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
    }
    public static LootTable.Builder villagePlainsChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.DANDELION).weight(2).quality(0))
                                .with(ItemEntry.builder(Items.POPPY).weight(0))
                                .with(ItemEntry.builder(Items.POTATO).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.APPLE).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(1).quality(2))
                                .with(ItemEntry.builder(Items.FEATHER).weight(1).quality(0))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.OAK_SAPLING).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
    public static LootTable.Builder villageTaigaHouseChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.FERN).weight(2).quality(0))
                                .with(ItemEntry.builder(Items.LARGE_FERN).weight(2).quality(0))
                                .with(ItemEntry.builder(Items.POTATO).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.SWEET_BERRIES).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.PUMPKIN_PIE).weight(1).quality(1))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.SPRUCE_SAPLING).weight(5).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.SPRUCE_SIGN).weight(0))
                                .with(ItemEntry.builder(Items.SPRUCE_LOG).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                );
    }
    public static LootTable.Builder villageSavannaHouseChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.SHORT_GRASS).weight(5).quality(0))
                                .with(ItemEntry.builder(Items.TALL_GRASS).weight(5).quality(0))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.WHEAT_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(2).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Blocks.ACACIA_SAPLING).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.SADDLE).weight(1))
                                .with(ItemEntry.builder(Blocks.TORCH).weight(1).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.BUCKET).weight(1).quality(1))
                );
    }
    public static LootTable.Builder villageSnowyHouseChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Blocks.BLUE_ICE).weight(1).quality(1))
                                .with(ItemEntry.builder(Blocks.SNOW_BLOCK).weight(4).quality(0))
                                .with(ItemEntry.builder(Items.POTATO).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                .with(ItemEntry.builder(Items.BEETROOT_SOUP).weight(1).quality(1))
                                .with(ItemEntry.builder(Items.FURNACE).weight(1).quality(0))
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.SNOWBALL).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.COAL).weight(5).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
    }
    public static LootTable.Builder villageDesertHouseChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.CLAY_BALL).weight(1).quality(0))
                                .with(ItemEntry.builder(Items.GREEN_DYE).weight(1).quality(1))
                                .with(ItemEntry.builder(Blocks.CACTUS).weight(10).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.WHEAT).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 7.0F))))
                                .with(ItemEntry.builder(Items.BREAD).weight(10).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.BOOK).weight(1).quality(2))
                                .with(ItemEntry.builder(Blocks.DEAD_BUSH).weight(2).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                .with(ItemEntry.builder(Items.EMERALD).weight(1).quality(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
    }
    public static LootTable.Builder ruinedPortalChest() {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(4.0F, 8.0F))
                                .bonusRolls(UniformLootNumberProvider.create(0f, 0.3f))
                                .with(ItemEntry.builder(Items.OBSIDIAN).weight(40).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                .with(ItemEntry.builder(Items.FLINT).weight(40).quality(0).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                                .with(ItemEntry.builder(Items.IRON_NUGGET).weight(40).quality(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(9.0F, 18.0F))))
                                .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(40).quality(0))
                                .with(ItemEntry.builder(Items.FIRE_CHARGE).weight(40).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(15).quality(2))
                                .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(15).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 24.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_SWORD).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_AXE).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_HOE).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_SHOVEL).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_PICKAXE).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_BOOTS).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_HELMET).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GOLDEN_LEGGINGS).weight(15).quality(3).apply(EnchantRandomlyLootFunction.builder()))
                                .with(ItemEntry.builder(Items.GLISTERING_MELON_SLICE).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.LIGHT_WEIGHTED_PRESSURE_PLATE).weight(5).quality(0))
                                .with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F))))
                                .with(ItemEntry.builder(Items.CLOCK).weight(5).quality(1))
                                .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 8.0F))))
                                .with(ItemEntry.builder(Items.BELL).weight(1).quality(1))
                                .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).quality(3))
                                .with(ItemEntry.builder(Items.GOLD_BLOCK).weight(1).quality(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
    }
}
