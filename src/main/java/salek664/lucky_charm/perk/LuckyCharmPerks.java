package salek664.lucky_charm.perk;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import salek664.lucky_charm.LuckyCharm;

import java.util.Optional;

public class LuckyCharmPerks {
    public static final RegistryKey<Registry<AttributeEquipmentPerk>> PERKS_KEY = RegistryKey.ofRegistry(new Identifier("perks"));
    public static final RegistryKey<AttributeEquipmentPerk> FORTUNED_TRAVELLER_PERK = RegistryKey.of(PERKS_KEY, new Identifier(LuckyCharm.MOD_ID, "fortuned_traveller"));
    public static final Text FORTUNED_TRAVELLER_INGREDIENTS_TEXT = Text.translatable(
                    Util.createTranslationKey("item", new Identifier(LuckyCharm.MOD_ID, "smithing_template.fortuned_traveller.ingredients"))
            )
            .formatted(Formatting.BLUE);
    public static final Text FORTUNED_TRAVELLER_TEXT = Text.translatable(
                    Util.createTranslationKey("perk", new Identifier(LuckyCharm.MOD_ID, "fortuned_traveller")))
            .formatted(Formatting.DARK_GREEN);
    public static final Text FORTUNED_TRAVELLER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", new Identifier(LuckyCharm.MOD_ID, "smithing_template.fortuned_traveller.additions_slot_description"))
    );
    public static void bootstrap(Registerable<AttributeEquipmentPerk> registry) {
        AttributeEquipmentPerk fortunedTravellerPerk = new AttributeEquipmentPerk(new AttributeEquipmentPerk.AttributeModifier(EntityAttributes.GENERIC_LUCK,
                "Fortuned Traveller", 1.0, EntityAttributeModifier.Operation.ADD_VALUE), FORTUNED_TRAVELLER_TEXT,true);
        registry.register(FORTUNED_TRAVELLER_PERK, fortunedTravellerPerk);
    }
    public static Optional<RegistryEntry.Reference<AttributeEquipmentPerk>> get(RegistryWrapper.WrapperLookup lookup, RegistryKey<AttributeEquipmentPerk> perkKey) {
        return lookup.getWrapperOrThrow(LuckyCharmPerks.PERKS_KEY).getOptional(perkKey);
    }
    public static void registerPerkRegistry() {
        DynamicRegistries.registerSynced(PERKS_KEY, AttributeEquipmentPerk.CODEC);
    }
}
