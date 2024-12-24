package salek664.lucky_charm.networking.payload;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;
import salek664.lucky_charm.networking.LuckyCharmNetworkingConstants;

import java.util.UUID;

public record LootTablesRequestC2SPacket(String namespace, UUID playerUUID) implements CustomPayload {
    public static final CustomPayload.Id<LootTablesRequestC2SPacket> ID = new CustomPayload.Id<>(LuckyCharmNetworkingConstants.LOOT_PACK_REQUEST_ID);
    public static final PacketCodec<RegistryByteBuf, LootTablesRequestC2SPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, LootTablesRequestC2SPacket::namespace,
            Uuids.PACKET_CODEC, LootTablesRequestC2SPacket::playerUUID,
            LootTablesRequestC2SPacket::new
    );
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
