package salek664.lucky_charm.item;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

public class LuckyCharmPotions {
    public static final RegistryEntry<Potion> LONG_LUCK = registerPotion("long_luck", "luck", new StatusEffectInstance(StatusEffects.LUCK, 9600, 0));
    public static final RegistryEntry<Potion> STRONG_LUCK = registerPotion("strong_luck", "luck", new StatusEffectInstance(StatusEffects.LUCK, 1800, 1));
    public static final RegistryEntry<Potion> UNLUCK = registerPotion("unluck", "unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 3600));
    public static final RegistryEntry<Potion> STRONG_UNLUCK = registerPotion("strong_unluck", "unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 3600, 1));
    public static final RegistryEntry<Potion> CURSE_UNLUCK = registerPotion("curse_unluck", "unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 6000, 3));
    private static RegistryEntry.Reference<Potion> registerPotion(String name, String basePotionName, StatusEffectInstance statusEffectInstance) {
        return Registry.registerReference(Registries.POTION,
                new Identifier(LuckyCharm.MOD_ID, name), new Potion(basePotionName, statusEffectInstance));
    }
    public static void registerPotionRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerRecipes(LuckyCharmItems.FOURLEAF_CLOVER, Potions.LUCK);
            builder.registerPotionRecipe(Potions.LUCK, Items.GLOWSTONE_DUST, STRONG_LUCK);
            builder.registerPotionRecipe(Potions.LUCK, Items.REDSTONE, LONG_LUCK);
        });
    }
}
