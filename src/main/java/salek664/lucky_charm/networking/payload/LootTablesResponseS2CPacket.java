package salek664.lucky_charm.networking.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import salek664.lucky_charm.networking.LuckyCharmNetworkingConstants;

import java.util.List;

public record LootTablesResponseS2CPacket(List<String> lootTables) implements CustomPayload {
    public static final CustomPayload.Id<LootTablesResponseS2CPacket> ID = new CustomPayload.Id<>(LuckyCharmNetworkingConstants.LOOT_PACK_RESPONSE_ID);
    public static final PacketCodec<RegistryByteBuf, LootTablesResponseS2CPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING.collect(PacketCodecs.toList()), LootTablesResponseS2CPacket::lootTables,
            LootTablesResponseS2CPacket::new
    );
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
