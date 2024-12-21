package salek664.lucky_charm.mixin.loot.criteria;

import net.minecraft.advancement.criterion.PlayerGeneratesContainerLootCriterion;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import salek664.lucky_charm.advancement.criterion.LuckyCharmCriteria;

@Mixin(PlayerGeneratesContainerLootCriterion.class)
public abstract class PlayerGeneratesContainerLootCriterionMixin {
    @Inject(
            method = "trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/registry/RegistryKey;)V",
            at = @At("HEAD")
    )
    private void triggerLuckContainerCriterion(ServerPlayerEntity player, RegistryKey<LootTable> lootTable, CallbackInfo ci) {
        LuckyCharmCriteria.PLAYER_OPENS_CHEST_WITH_LUCK.trigger(player, lootTable, player.getLuck());
    }
}
