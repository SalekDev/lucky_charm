package salek664.lucky_charm.perk;

import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

import java.util.List;
import java.util.function.UnaryOperator;

public class PerkDataComponent {
    public static final ComponentType<List<AttributeEquipmentPerk>> INSTANCE = register(
            "perks", builder -> builder.codec(AttributeEquipmentPerk.LIST_CODEC).packetCodec(AttributeEquipmentPerk.PACKET_CODEC.collect(PacketCodecs.toList())).cache()
    );
    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(LuckyCharm.MOD_ID, id),
                ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }
    public static void registerPerkDataComponent() {
        LuckyCharm.LOGGER.info("Registering Data Component");
    }
}
