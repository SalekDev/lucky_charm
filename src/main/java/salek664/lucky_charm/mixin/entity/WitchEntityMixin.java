package salek664.lucky_charm.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import salek664.lucky_charm.item.LuckyCharmPotions;

@Mixin(WitchEntity.class)
public abstract class WitchEntityMixin extends RaiderEntity implements RangedAttackMob {
    protected WitchEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }
    @ModifyVariable(
            method = "shootAt",
            at = @At(value = "STORE", ordinal = 3)
    )
    private RegistryEntry<Potion> addUnluckPotionAttack(RegistryEntry<Potion> registryEntry, LivingEntity target, float pullProgress) {
        double targetLuck = target.getAttributeValue(EntityAttributes.LUCK);
        if (target.getRandom().nextFloat() < 0.5f && !target.hasStatusEffect(StatusEffects.UNLUCK)) {
            return targetLuck <= 0 ? LuckyCharmPotions.UNLUCK :
                    targetLuck <= 2 ? LuckyCharmPotions.STRONG_UNLUCK : LuckyCharmPotions.CURSE_UNLUCK;
        } else return registryEntry;
    }
}
