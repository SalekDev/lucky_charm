package salek664.lucky_charm;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import salek664.lucky_charm.client.gui.screen.LootScreenConstants;
import salek664.lucky_charm.client.gui.screen.loot.LootTableEditorScreen;
import salek664.lucky_charm.networking.LuckyCharmNetworkingConstants;
import salek664.lucky_charm.networking.payload.LootTablesResponseS2CPacket;

import java.util.List;

public class LuckyCharmClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(LootTablesResponseS2CPacket.ID, ((payload, context) -> {
            try (MinecraftClient client = context.client()) {
                if (client.currentScreen instanceof LootTableEditorScreen lootTableEditorScreen) {
                    List<String> list = payload.lootTables();
                    if (list.size() <= LootScreenConstants.TABLE_LIST_LIMIT && !list.isEmpty()) {
                        lootTableEditorScreen.lootTableOrganizer.acceptInfo(list);
                    }
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }));
    }
}
