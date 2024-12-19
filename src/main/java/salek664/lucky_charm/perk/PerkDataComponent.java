package salek664.lucky_charm.perk;

import net.minecraft.component.DataComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

import java.util.List;
import java.util.function.UnaryOperator;

public class PerkDataComponent {
    public static final DataComponentType<List<AttributeEquipmentPerk>> INSTANCE = register(
            "perks", builder -> builder.codec(AttributeEquipmentPerk.LIST_CODEC).packetCodec(AttributeEquipmentPerk.PACKET_CODEC.collect(PacketCodecs.toList())).cache()
    );
    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, new Identifier(LuckyCharm.MOD_ID, id),
                ((DataComponentType.Builder)builderOperator.apply(DataComponentType.builder())).build());
    }
    public static void registerPerkDataComponent() {
        LuckyCharm.LOGGER.info("Registering Data Component");
    }
}
