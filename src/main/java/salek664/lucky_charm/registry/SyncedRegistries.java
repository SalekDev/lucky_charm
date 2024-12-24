package salek664.lucky_charm.registry;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryLoader;
import salek664.lucky_charm.mixin.registry.SerializableRegistriesAccessor;

public class SyncedRegistries {
    public static void makeLootTablesSyncedRegistry() {
        RegistryLoader.SYNCED_REGISTRIES.add(new RegistryLoader.Entry<>(RegistryKeys.LOOT_TABLE, LootTable.CODEC, false));
        SerializableRegistriesAccessor.getSyncedRegistries().add(RegistryKeys.LOOT_TABLE);
    }
}
