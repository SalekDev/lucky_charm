package salek664.lucky_charm.mixin.client.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.TextIconButtonWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.client.gui.screen.loot.LootTableEditorScreen;

@Mixin(PackScreen.class)
public abstract class PackScreenMixin extends Screen {
    @Shadow
    ThreePartsLayoutWidget layout;
    @Shadow
    ResourcePackOrganizer organizer;
    @Unique
    private TextIconButtonWidget luckyCharmButton;
    protected PackScreenMixin(Text title) {
        super(title);
    }
    @Inject(
            method = "init",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/DirectionalLayoutWidget;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 2)
    )
    private void addLuckyCharmButton(CallbackInfo ci) {
        luckyCharmButton = this.addDrawableChild(
                TextIconButtonWidget.builder(Text.translatable(
                        Util.createTranslationKey("button", Identifier.of(LuckyCharm.MOD_ID, "editing_menu"))
                ), bt -> this.client.setScreen(
                        new LootTableEditorScreen(this, this.client.getResourcePackManager(), ((ResourcePackOrganizerAccessor) organizer).getApplier())
                        ), true)
                        .texture(Identifier.of(LuckyCharm.MOD_ID, "four_leaf_clover"), 15, 15)
                        .width(20)
                        .build()
        );

    }
    @Inject(
            method = "refreshWidgetPositions",
            at = @At("RETURN")
    )
    private void refreshButtonPosition(CallbackInfo ci) {
        luckyCharmButton.setY(layout.getHeaderHeight() + layout.getContentHeight() + 7);
        luckyCharmButton.setX(this.width / 2 - 10 - 200);
    }
}
