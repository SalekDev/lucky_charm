package salek664.lucky_charm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.item.LuckyCharmItems;
import salek664.lucky_charm.perk.PerkTrimRecipe;

import java.util.concurrent.CompletableFuture;

public class LuckyCharmRecipeProvider extends FabricRecipeProvider {
    public LuckyCharmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE, 2)
                .input('#', Items.COPPER_INGOT)
                .input('C', Blocks.EMERALD_BLOCK)
                .input('S', LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .criterion(hasItem(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE), conditionsFromItem(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, LuckyCharmItems.LUCKY_CHARM_BOWL)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.BOWL)
                .input(Items.SUGAR)
                .input(LuckyCharmItems.THREELEAF_CLOVER)
                .input(LuckyCharmItems.THREELEAF_CLOVER)
                .input(Items.MILK_BUCKET)
                .criterion(hasItem(LuckyCharmItems.FOURLEAF_CLOVER), conditionsFromItem(LuckyCharmItems.FOURLEAF_CLOVER))
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier(LuckyCharm.MOD_ID, getItemPath(LuckyCharmItems.LUCKY_CHARM_BOWL)));
        PerkTrimRecipe.PerkTrimRecipeJsonBuilder.create(
                        Ingredient.ofItems(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE),
                        Ingredient.fromTag(ItemTags.TRIMMABLE_ARMOR),
                        Ingredient.ofItems(LuckyCharmItems.FORTUNE_GEM), RecipeCategory.MISC)
                .criterion(hasItem(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE), conditionsFromItem(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE))
                .offerTo(exporter, new Identifier(LuckyCharm.MOD_ID, getItemPath(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE) + "_smithing"));
    }
}
