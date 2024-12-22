package salek664.lucky_charm;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import salek664.lucky_charm.advancement.criterion.LuckyCharmCriteria;
import salek664.lucky_charm.config.LuckyCharmConfigReader;
import salek664.lucky_charm.config.LuckyCharmLootTableLoader;
import salek664.lucky_charm.item.LuckyCharmItemGroups;
import salek664.lucky_charm.item.LuckyCharmItems;
import salek664.lucky_charm.item.LuckyCharmPotions;
import salek664.lucky_charm.perk.LuckyCharmPerks;
import salek664.lucky_charm.perk.PerkDataComponent;
import salek664.lucky_charm.perk.PerkTrimRecipe;
import salek664.lucky_charm.loot.LuckyCharmBlockLootTableModifiers;
import salek664.lucky_charm.recipe.LuckyCharmRecipes;

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
	}
}