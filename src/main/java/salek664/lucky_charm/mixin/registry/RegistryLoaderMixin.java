package salek664.lucky_charm.mixin.registry;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(RegistryLoader.class)
public abstract class RegistryLoaderMixin {
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;of([Ljava/lang/Object;)Ljava/util/List;",
                    ordinal = 1
            )
    )
    private static List<RegistryLoader.Entry<?>> modifySyncedRegistries(List<RegistryLoader.Entry<?>> original) {
        return ImmutableList.<RegistryLoader.Entry<?>>builder().addAll(original)
                .add(
                        new RegistryLoader.Entry<>(RegistryKeys.LOOT_TABLE, LootTable.CODEC, false)
                ).build();
    }
}
