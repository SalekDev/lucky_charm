package salek664.lucky_charm;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.*;
import salek664.lucky_charm.datagen.*;
import salek664.lucky_charm.perk.LuckyCharmPerks;

public class LuckyCharmDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		//pack.addProvider(LuckyCharmModelProvider::new);
		pack.addProvider(LuckyCharmChestLootTableDataPackProvider::new);
		pack.addProvider(LuckyCharmBlockTagProvider::new);
		pack.addProvider(LuckyCharmItemTagProvider::new);
		pack.addProvider(LuckyCharmRecipeProvider::new);
		pack.addProvider(LuckyCharmDynamicRegistriesProvider::new);
		pack.addProvider(LuckyCharmAdvancementProvider::new);
	}
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(LuckyCharmPerks.PERKS_KEY, LuckyCharmPerks::bootstrap);
	}
}
