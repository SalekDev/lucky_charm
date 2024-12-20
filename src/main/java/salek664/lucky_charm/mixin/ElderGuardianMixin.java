package salek664.lucky_charm.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ElderGuardianEntity.class)
public abstract class ElderGuardianMixin extends GuardianEntity {
    public ElderGuardianMixin(EntityType<? extends GuardianEntity> entityType, World world) {
        super(entityType, world);
    }
    @ModifyVariable(
            method = "mobTick",
            at = @At(value = "STORE", ordinal = 0)
    )
    private StatusEffectInstance randomUnluckChance(StatusEffectInstance original) {
        Random random = ((ElderGuardianEntity) (Object) this).getRandom();
        return random.nextInt(9) == 0 ? new StatusEffectInstance(StatusEffects.UNLUCK, 6000, 3) : original;
    }
}
