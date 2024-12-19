package salek664.lucky_charm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;
import salek664.lucky_charm.perk.LuckyCharmPerks;

import java.util.concurrent.CompletableFuture;

public class LuckyCharmDynamicRegistriesProvider extends FabricDynamicRegistryProvider {
    public LuckyCharmDynamicRegistriesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(LuckyCharmPerks.PERKS_KEY));
    }
    @Override
    public String getName() {
        return "Lucky Charm Dynamic Registries";
    }
}
