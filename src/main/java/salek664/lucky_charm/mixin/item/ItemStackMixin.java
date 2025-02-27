package salek664.lucky_charm.mixin.item;

import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.ComponentHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import salek664.lucky_charm.perk.AttributeEquipmentPerk;
import salek664.lucky_charm.perk.PerkDataComponent;

import java.util.List;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ComponentHolder, FabricItemStack {
    @Unique
    private void appendPerksTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type) {
        List<AttributeEquipmentPerk> perksList = this.getOrDefault(PerkDataComponent.INSTANCE, List.of());
        if (perksList != null && !perksList.isEmpty()) {
            textConsumer.accept(AttributeEquipmentPerk.PERKS_TEXT);
            perksList.forEach(equipmentPerk -> equipmentPerk.appendTooltip(context, textConsumer, type));
        }
    }
    @Inject(method = "Lnet/minecraft/item/ItemStack;getTooltip(Lnet/minecraft/item/Item$TooltipContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipType;)Ljava/util/List;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;appendTooltip(Lnet/minecraft/component/DataComponentType;Lnet/minecraft/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/client/item/TooltipType;)V", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    private void addPerksToTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type,
                                   CallbackInfoReturnable<List<Text>> ci, List<Text> list, MutableText mutableText, Consumer<Text> consumer) {
        this.appendPerksTooltip(context, consumer, type);
    }
}
