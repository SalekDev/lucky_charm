package salek664.lucky_charm;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConfigurationNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import salek664.lucky_charm.advancement.criterion.LuckyCharmCriteria;
import salek664.lucky_charm.config.LuckyCharmConfigReader;
import salek664.lucky_charm.config.LuckyCharmLootTableLoader;
import salek664.lucky_charm.item.LuckyCharmItemGroups;
import salek664.lucky_charm.item.LuckyCharmItems;
import salek664.lucky_charm.item.LuckyCharmPotions;
import salek664.lucky_charm.networking.LuckyCharmNetworkingConstants;
import salek664.lucky_charm.networking.payload.LootTablesRequestC2SPacket;
import salek664.lucky_charm.networking.payload.LootTablesResponseS2CPacket;
import salek664.lucky_charm.perk.LuckyCharmPerks;
import salek664.lucky_charm.perk.PerkDataComponent;
import salek664.lucky_charm.perk.PerkTrimRecipe;
import salek664.lucky_charm.loot.LuckyCharmBlockLootTableModifiers;
import salek664.lucky_charm.recipe.LuckyCharmRecipes;
import salek664.lucky_charm.registry.SyncedRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LuckyCharm implements ModInitializer {
	public static final String MOD_ID = "lucky_charm";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Initializing lucky charm mod");
		LuckyCharmLootTableLoader.loadSettings(LuckyCharmConfigReader.getCurrentConfig());
		LuckyCharmItems.registerItems();
		LuckyCharmRecipes.registerRecipes();
		LuckyCharmItemGroups.registerItemGroups();
		LuckyCharmBlockLootTableModifiers.registerModifiers();
		PerkTrimRecipe.registerSerializer();
		PerkDataComponent.registerPerkDataComponent();
		LuckyCharmPerks.registerPerkRegistry();
		LuckyCharmPotions.registerPotionRecipes();
		LuckyCharmCriteria.registerCriteria();
		LuckyCharmNetworkingConstants.registerPayloads();
		SyncedRegistries.makeLootTablesSyncedRegistry();
		ServerPlayNetworking.registerGlobalReceiver(LootTablesRequestC2SPacket.ID, ((payload, context) -> {
			try (MinecraftServer server = context.server()) {
				List<String> lootTables = server.getRegistryManager().getOrThrow(RegistryKeys.LOOT_TABLE).getKeys()
						.stream().filter(key -> key.getValue().getNamespace().contentEquals(payload.namespace()))
						.map(key -> key.getValue().getPath()).toList();
				if (server.getPlayerManager().getPlayer(payload.playerUUID()) instanceof ServerPlayerEntity player) {
					ServerPlayNetworking.send(player, new LootTablesResponseS2CPacket(lootTables));
				}
			} catch (Exception e) {
				e.fillInStackTrace();
			}
		}));
	}
}