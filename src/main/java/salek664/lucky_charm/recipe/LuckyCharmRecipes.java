package salek664.lucky_charm.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

public class LuckyCharmRecipes {
    public static final RegistryKey<Recipe<?>> FORTUNED_TRAVELLER_RECIPE_SMITHING = RegistryKey.of(RegistryKeys.RECIPE,
            Identifier.of(LuckyCharm.MOD_ID, "fortuned_traveller_recipe_smithing"));
    public static void registerRecipes() {
        LuckyCharm.LOGGER.info("Registering Recipes for " + LuckyCharm.MOD_ID);
    }
}
