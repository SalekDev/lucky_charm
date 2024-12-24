package salek664.lucky_charm.perk;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.recipe.display.SmithingRecipeDisplay;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import salek664.lucky_charm.LuckyCharm;

import java.util.*;

public class PerkTrimRecipe implements SmithingRecipe {
    private static final RecipeSerializer<PerkTrimRecipe> PERK_TRIM_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER,
            Identifier.of(LuckyCharm.MOD_ID, "perk_trim"), new Serializer());
    public final Optional<Ingredient> template;
    public final Optional<Ingredient> base;
    public final Optional<Ingredient> addition;
    @Nullable
    private IngredientPlacement ingredientPlacement;
    public PerkTrimRecipe(Optional<Ingredient> template, Optional<Ingredient> base, Optional<Ingredient> addition) {
        this.template = template;
        this.base = base;
        this.addition = addition;
    }
    @Override
    public ItemStack craft(SmithingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack equipment = input.base();
        if (this.base.isEmpty()) return ItemStack.EMPTY;
        if (this.base.get().test(equipment) && input.template().getItem() instanceof PerkSmithingTemplate perkTemplate) {
            Optional<RegistryEntry.Reference<AttributeEquipmentPerk>> maybePerk = LuckyCharmPerks.get(lookup, perkTemplate.perk);
            if (maybePerk.isPresent() && !input.addition().isEmpty()) {
                List<AttributeEquipmentPerk> perkList = equipment.get(PerkDataComponent.INSTANCE);
                AttributeEquipmentPerk actualPerk = maybePerk.get().value();
                if (perkList != null) {
                    if (perkList.stream().noneMatch(perk -> perk.equals(actualPerk))) {
                        perkList.add(actualPerk);
                        return upgradeEquipment(equipment, perkList, actualPerk);
                    }
                } else {
                    perkList = List.of(actualPerk);
                    return upgradeEquipment(equipment, perkList, actualPerk);
                }
            }
        }
        return ItemStack.EMPTY;
    }
    @NotNull
    private static ItemStack upgradeEquipment(ItemStack equipment, List<AttributeEquipmentPerk> perkList, AttributeEquipmentPerk actualPerk) {
        EquippableComponent equippableComponent = equipment.get(DataComponentTypes.EQUIPPABLE);
        ItemStack upgradedEquipment = equipment.copyWithCount(1);
        if (equippableComponent != null) {
            EquipmentSlot slot = equippableComponent.slot();
            EntityAttributeModifier modifier = actualPerk.extractNewModifier(slot.getName());
            AttributeModifiersComponent finalModifiersComponent = equipment.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            AttributeModifiersComponent.DEFAULT)
                    .with(actualPerk.attributeModifier().attribute(), modifier, AttributeModifierSlot.forEquipmentSlot(slot));
            equipment.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                    .modifiers()
                    .forEach(modifier1 -> finalModifiersComponent.with(modifier1.attribute(), modifier1.modifier(), modifier1.slot()));
            upgradedEquipment.set(PerkDataComponent.INSTANCE, perkList);
            upgradedEquipment.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, finalModifiersComponent);
        }
        return upgradedEquipment;
    }
    public static void registerSerializer() {
        LuckyCharm.LOGGER.info("Registering Perk Recipe Serializer");
    }
    @Override
    public RecipeSerializer<PerkTrimRecipe> getSerializer() {
        return PERK_TRIM_SERIALIZER;
    }
    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(this.template, this.base, this.addition));
        }

        return this.ingredientPlacement;
    }
    @Override
    public List<RecipeDisplay> getDisplays() {
        SlotDisplay slotDisplay = Ingredient.toDisplay(this.base);
        SlotDisplay slotDisplay2 = Ingredient.toDisplay(this.addition);
        SlotDisplay slotDisplay3 = Ingredient.toDisplay(this.template);
        return List.of(
                new SmithingRecipeDisplay(
                        slotDisplay3,
                        slotDisplay,
                        slotDisplay2,
                        new SlotDisplay.SmithingTrimSlotDisplay(slotDisplay, slotDisplay2, slotDisplay3),
                        new SlotDisplay.ItemSlotDisplay(Items.SMITHING_TABLE)
                )
        );
    }
    @Override
    public Optional<Ingredient> template() {
        return this.template;
    }
    @Override
    public Optional<Ingredient> base() {
        return this.base;
    }
    @Override
    public Optional<Ingredient> addition() {
        return this.addition;
    }
    public static class Serializer implements RecipeSerializer<PerkTrimRecipe> {
        private static final MapCodec<PerkTrimRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Ingredient.CODEC.optionalFieldOf("template").forGetter(recipe -> recipe.template),
                                Ingredient.CODEC.optionalFieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.CODEC.optionalFieldOf("addition").forGetter(recipe -> recipe.addition)
                        ).apply(instance, PerkTrimRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, PerkTrimRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                PerkTrimRecipe.Serializer::write, PerkTrimRecipe.Serializer::read);
        @Override
        public MapCodec<PerkTrimRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, PerkTrimRecipe> packetCodec() {
            return PACKET_CODEC;
        }
        private static PerkTrimRecipe read(RegistryByteBuf buf) {
            Optional<Ingredient> ingredient = Ingredient.OPTIONAL_PACKET_CODEC.decode(buf);
            Optional<Ingredient> ingredient2 = Ingredient.OPTIONAL_PACKET_CODEC.decode(buf);
            Optional<Ingredient> ingredient3 = Ingredient.OPTIONAL_PACKET_CODEC.decode(buf);
            return new PerkTrimRecipe(ingredient, ingredient2, ingredient3);
        }
        private static void write(RegistryByteBuf buf, PerkTrimRecipe recipe) {
            Ingredient.OPTIONAL_PACKET_CODEC.encode(buf, recipe.template);
            Ingredient.OPTIONAL_PACKET_CODEC.encode(buf, recipe.base);
            Ingredient.OPTIONAL_PACKET_CODEC.encode(buf, recipe.addition);
        }
    }
    public static class PerkTrimRecipeJsonBuilder {
        private final RecipeCategory category;
        private final Ingredient template;
        private final Ingredient base;
        private final Ingredient addition;
        private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
        public PerkTrimRecipeJsonBuilder(RecipeCategory category, Ingredient template, Ingredient base, Ingredient addition) {
            this.category = category;
            this.template = template;
            this.base = base;
            this.addition = addition;
        }
        public static PerkTrimRecipeJsonBuilder create(Ingredient template, Ingredient base, Ingredient addition, RecipeCategory category) {
            return new PerkTrimRecipeJsonBuilder(category, template, base, addition);
        }
        public PerkTrimRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
            this.criteria.put(name, criterion);
            return this;
        }
        public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
            Identifier recipeId = recipeKey.getValue();
            this.validate(recipeId);
            Advancement.Builder builder = exporter.getAdvancementBuilder()
                    .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                    .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                    .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
            this.criteria.forEach(builder::criterion);
            PerkTrimRecipe perkTrimRecipe = new PerkTrimRecipe(Optional.of(this.template), Optional.of(this.base), Optional.of(this.addition));
            exporter.accept(recipeKey, perkTrimRecipe, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")));
        }
        private void validate(Identifier recipeId) {
            if (this.criteria.isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + recipeId);
            }
        }
    }
}
