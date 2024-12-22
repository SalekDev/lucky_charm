package salek664.lucky_charm.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class LuckyCharmFoodComponents {
    public static final FoodComponent LUCKY_CHARM_CEREAL = new FoodComponent.Builder().alwaysEdible().nutrition(4).saturationModifier(0.3f)
            .build();
    public static final ConsumableComponent LUCKY_CEREAL = ConsumableComponents.food().consumeEffect(
            new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.LUCK, 400, 0))).build();
}
