package salek664.lucky_charm.perk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import salek664.lucky_charm.LuckyCharm;

import java.util.List;
import java.util.function.Consumer;

public record AttributeEquipmentPerk(AttributeModifier attributeModifier, Text name, boolean showInTooltip) implements TooltipAppender {
    public static final Text PERKS_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(LuckyCharm.MOD_ID, "perks")))
            .formatted(Formatting.GRAY);
    public static final Codec<AttributeEquipmentPerk> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    AttributeModifier.CODEC.fieldOf("attribute_modifier").forGetter(AttributeEquipmentPerk::attributeModifier),
                    TextCodecs.CODEC.fieldOf("name").forGetter(AttributeEquipmentPerk::name),
                    Codec.BOOL.optionalFieldOf("show_in_tooltip", Boolean.TRUE).forGetter(AttributeEquipmentPerk::showInTooltip)
            ).apply(instance, AttributeEquipmentPerk::new)
    );
    public static final Codec<List<AttributeEquipmentPerk>> LIST_CODEC = CODEC.listOf();
    public static final PacketCodec<RegistryByteBuf, AttributeEquipmentPerk> PACKET_CODEC = PacketCodec.tuple(
            AttributeModifier.PACKET_CODEC,
            AttributeEquipmentPerk::attributeModifier,
            TextCodecs.PACKET_CODEC,
            AttributeEquipmentPerk::name,
            PacketCodecs.BOOL,
            AttributeEquipmentPerk::showInTooltip,
            AttributeEquipmentPerk::new
    );
    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        if (this.showInTooltip) tooltip.accept(ScreenTexts.space().append(this.name));
    }
    public EntityAttributeModifier extractNewModifier(String customId) {
        return new EntityAttributeModifier(Identifier.of(LuckyCharm.MOD_ID, this.attributeModifier().name() + "." + customId), this.attributeModifier().value(), this.attributeModifier().operation());
    }
    public boolean equals(AttributeEquipmentPerk perk) {
        return perk.name().getString().equals(this.name.getString());
    }
    public record AttributeModifier(RegistryEntry<EntityAttribute> attribute, String name, double value, EntityAttributeModifier.Operation operation) {
        public static final Codec<AttributeModifier> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                        Registries.ATTRIBUTE.getEntryCodec().fieldOf("modified_attribute").forGetter(AttributeModifier::attribute),
                        Codec.STRING.fieldOf("name").forGetter(AttributeModifier::name),
                        Codec.DOUBLE.fieldOf("value").forGetter(AttributeModifier::value),
                        EntityAttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(AttributeModifier::operation)
                ).apply(instance, AttributeModifier::new)
        );
        public static final PacketCodec<RegistryByteBuf, AttributeModifier> PACKET_CODEC = PacketCodec.tuple(
                PacketCodecs.registryEntry(RegistryKeys.ATTRIBUTE),
                AttributeModifier::attribute,
                PacketCodecs.STRING,
                AttributeModifier::name,
                PacketCodecs.DOUBLE,
                AttributeModifier::value,
                EntityAttributeModifier.Operation.PACKET_CODEC,
                AttributeModifier::operation,
                AttributeModifier::new
        );
    }
}
