package salek664.lucky_charm.mixin.registry;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SerializableRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(SerializableRegistries.class)
public interface SerializableRegistriesAccessor {
    @Accessor("SYNCED_REGISTRIES")
    static Set<RegistryKey<? extends Registry<?>>> getSyncedRegistries() {
        throw new AssertionError();
    }
}
