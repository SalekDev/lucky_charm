package salek664.lucky_charm.item;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

public class LuckyCharmPotions {
    public static final RegistryEntry<Potion> UNLUCK = Registry.registerReference(Registries.POTION,
            new Identifier(LuckyCharm.MOD_ID, "unluck"), new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 3600)));
    public static final RegistryEntry<Potion> STRONG_UNLUCK = Registry.registerReference(Registries.POTION,
            new Identifier(LuckyCharm.MOD_ID, "strong_unluck"), new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 3600, 1)));
    public static final RegistryEntry<Potion> CURSE_UNLUCK = Registry.registerReference(Registries.POTION,
            new Identifier(LuckyCharm.MOD_ID, "curse_unluck"), new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 7200, 3)));
    public static void registerPotionRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> builder.registerRecipes(LuckyCharmItems.FOURLEAF_CLOVER, Potions.LUCK));
    }
}
