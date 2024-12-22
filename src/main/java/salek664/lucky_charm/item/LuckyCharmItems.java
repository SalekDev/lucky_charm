package salek664.lucky_charm.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.perk.LuckyCharmPerks;
import salek664.lucky_charm.perk.PerkSmithingTemplate;
import salek664.lucky_charm.mixin.item.SmithingTemplateItemAccessor;

public class LuckyCharmItems {
    public static final Item THREELEAF_CLOVER = register("three_leaf_clover", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LuckyCharm.MOD_ID, "three_leaf_clover")))));
    public static final Item FOURLEAF_CLOVER = register("four_leaf_clover", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LuckyCharm.MOD_ID, "four_leaf_clover")))));
    public static final Item LUCKY_CHARM_BOWL = register("lucky_charm_bowl", new Item(new Item.Settings()
            .maxCount(1).food(LuckyCharmFoodComponents.LUCKY_CHARM_CEREAL, LuckyCharmFoodComponents.LUCKY_CEREAL)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LuckyCharm.MOD_ID, "lucky_charm_bowl")))));
    public static final Item FORTUNED_TRAVELLER_SMITHING_TEMPLATE = register("fortuned_traveller_smithing_template",
            new PerkSmithingTemplate(
                    SmithingTemplateItemAccessor.getArmorTrimText(),
                    LuckyCharmPerks.FORTUNED_TRAVELLER_INGREDIENTS_TEXT,
                    Text.translatable(Util.createTranslationKey("trim_pattern", Identifier.of(LuckyCharm.MOD_ID, "fortuned_traveller")))
                            .formatted(Formatting.DARK_GREEN),
                    SmithingTemplateItemAccessor.getArmorTrimBaseSlotDescriptionText(),
                    LuckyCharmPerks.FORTUNED_TRAVELLER_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                    SmithingTemplateItemAccessor.invokeGetArmorTrimBaseSlotTextures(),
                    SmithingTemplateItemAccessor.invokeGetArmorAdditionsSlotTextures(),
                    new Item.Settings().rarity(Rarity.RARE).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LuckyCharm.MOD_ID, "fortuned_traveller_smithing_template"))),
                    LuckyCharmPerks.FORTUNED_TRAVELLER_PERK
            )
    );
    public static final Item FORTUNE_GEM = register("fortune_gem", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LuckyCharm.MOD_ID, "fortune_gem")))));
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LuckyCharm.MOD_ID, name), item);
    }
    public static void registerItems() {
        LuckyCharm.LOGGER.info("Registering Items for " + LuckyCharm.MOD_ID);
    }
}
