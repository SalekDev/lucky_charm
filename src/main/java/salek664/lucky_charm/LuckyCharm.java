package salek664.lucky_charm;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import salek664.lucky_charm.config.LootMixinDisabler;
import salek664.lucky_charm.item.LuckyCharmItemGroups;
import salek664.lucky_charm.item.LuckyCharmItems;
import salek664.lucky_charm.item.LuckyCharmPotions;
import salek664.lucky_charm.loot.hyper.LuckyCharmChestHyperModifiers;
import salek664.lucky_charm.loot.nonHyperModifiers.LuckyCharmArcheologyLootTableModifiers;
import salek664.lucky_charm.perk.LuckyCharmPerks;
import salek664.lucky_charm.perk.PerkDataComponent;
import salek664.lucky_charm.perk.PerkTrimRecipe;
import salek664.lucky_charm.loot.nonHyperModifiers.LuckyCharmBlockLootTableModifiers;
import salek664.lucky_charm.loot.nonHyperModifiers.LuckyCharmChestLootTableModifiers;

public class LuckyCharm implements ModInitializer {
	public static final String MOD_ID = "lucky_charm";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Initializing lucky charm mod");
		LuckyCharmItems.registerItems();
		LuckyCharmItemGroups.registerItemGroups();
		LootTableEvents.REPLACE.register((key, original, source) -> {
			if (LootMixinDisabler.doHyperLoot) {
				return LuckyCharmChestHyperModifiers.attemptReplace(key, source)
							.orElse(original);
			} else {
				return LuckyCharmArcheologyLootTableModifiers.attemptReplace(key, source)
						.orElse(LuckyCharmChestLootTableModifiers.attemptReplace(key, source)
								.orElse(original));
			}
		});
		LuckyCharmBlockLootTableModifiers.registerModifiers();
		PerkTrimRecipe.registerSerializer();
		PerkDataComponent.registerPerkDataComponent();
		LuckyCharmPerks.registerPerkRegistry();
		LuckyCharmPotions.registerPotionRecipes();
	}
}