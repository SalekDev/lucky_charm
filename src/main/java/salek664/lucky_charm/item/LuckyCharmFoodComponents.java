package salek664.lucky_charm.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class LuckyCharmFoodComponents {
    public static final FoodComponent LUCKY_CHARM_CEREAL = new FoodComponent.Builder().alwaysEdible().nutrition(4).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 400, 0), 1.0f).build();
}
