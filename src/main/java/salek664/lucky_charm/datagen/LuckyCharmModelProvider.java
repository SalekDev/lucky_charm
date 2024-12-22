package salek664.lucky_charm.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import salek664.lucky_charm.item.LuckyCharmItems;

public class LuckyCharmModelProvider extends FabricModelProvider {
    public LuckyCharmModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(LuckyCharmItems.THREELEAF_CLOVER, Models.GENERATED);
        itemModelGenerator.register(LuckyCharmItems.FOURLEAF_CLOVER, Models.GENERATED);
        itemModelGenerator.register(LuckyCharmItems.LUCKY_CHARM_BOWL, Models.GENERATED);
        itemModelGenerator.register(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(LuckyCharmItems.FORTUNE_GEM, Models.GENERATED);
    }
}
