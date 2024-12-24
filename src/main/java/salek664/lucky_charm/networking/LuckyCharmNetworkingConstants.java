package salek664.lucky_charm.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.networking.payload.LootTablesRequestC2SPacket;
import salek664.lucky_charm.networking.payload.LootTablesResponseS2CPacket;

public class LuckyCharmNetworkingConstants {
    public static final Identifier LOOT_PACK_REQUEST_ID = Identifier.of(LuckyCharm.MOD_ID, "loot_pack_request_c2s_paylod");
    public static final Identifier LOOT_PACK_RESPONSE_ID = Identifier.of(LuckyCharm.MOD_ID, "loot_pack_response_c2s_paylod");
    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(LootTablesRequestC2SPacket.ID, LootTablesRequestC2SPacket.CODEC);
        PayloadTypeRegistry.playS2C().register(LootTablesResponseS2CPacket.ID, LootTablesResponseS2CPacket.CODEC);
    }
}
