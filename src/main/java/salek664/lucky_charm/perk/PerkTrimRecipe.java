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
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import salek664.lucky_charm.LuckyCharm;

import java.util.*;
import java.util.stream.Stream;

public class PerkTrimRecipe implements SmithingRecipe {
    private static final RecipeSerializer<PerkTrimRecipe> PERK_TRIM_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER,
            new Identifier(LuckyCharm.MOD_ID, "perk_trim"), new Serializer());
    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;
    public PerkTrimRecipe(Ingredient template, Ingredient base, Ingredient addition) {
        this.template = template;
        this.base = base;
        this.addition = addition;
    }
    @Override
    public boolean testTemplate(ItemStack stack) {
        return this.template.test(stack);
    }
    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }
    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }
    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.template.test(inventory.getStack(0)) &&
                this.base.test(inventory.getStack(1)) &&
                this.addition.test(inventory.getStack(2));
    }
    @Override
    public ItemStack craft(Inventory inventory, RegistryWrapper.WrapperLookup lookup) {
        ItemStack equipment = inventory.getStack(1);
        if (this.base.test(equipment) && inventory.getStack(0).getItem() instanceof PerkSmithingTemplate perkTemplate) {
            Optional<RegistryEntry.Reference<AttributeEquipmentPerk>> maybePerk = LuckyCharmPerks.get(lookup, perkTemplate.perk);
            if (maybePerk.isPresent() && !inventory.getStack(2).isEmpty()) {
                List<AttributeEquipmentPerk> perkList = equipment.get(PerkDataComponent.INSTANCE);
                AttributeEquipmentPerk actualPerk = maybePerk.get().value();
                if (perkList != null) {
                    if (perkList.stream().noneMatch(perk -> perk.equals(actualPerk))) {
                        perkList.add(actualPerk);
                        ItemStack upgradedEquipment = upgradeEquipment(equipment, perkList, actualPerk);
                        return upgradedEquipment;
                    }
                } else {
                    perkList = List.of(actualPerk);
                    ItemStack upgradedEquipment = upgradeEquipment(equipment, perkList, actualPerk);
                    return upgradedEquipment;
                }
            }
        }
        return ItemStack.EMPTY;
    }
    @NotNull
    private static ItemStack upgradeEquipment(ItemStack equipment, List<AttributeEquipmentPerk> perkList, AttributeEquipmentPerk actualPerk) {
        ArmorItem armorItem = (ArmorItem) equipment.getItem();
        EquipmentSlot slot = armorItem.getSlotType();
        EntityAttributeModifier modifier = actualPerk.extractNewModifier();
        AttributeModifiersComponent finalModifiersComponent = armorItem.getAttributeModifiers()
                .with(actualPerk.attributeModifier().attribute(), modifier, AttributeModifierSlot.forEquipmentSlot(slot));
        equipment.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)
                .modifiers()
                .forEach(modifier1 -> finalModifiersComponent.with(modifier1.attribute(), modifier1.modifier(), modifier1.slot()));
        ItemStack upgradedEquipment = equipment.copyWithCount(1);
        upgradedEquipment.set(PerkDataComponent.INSTANCE, perkList);
        upgradedEquipment.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, finalModifiersComponent);
        return upgradedEquipment;
    }
    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return new ItemStack(Items.IRON_CHESTPLATE);
    }
    public static void registerSerializer() {
        LuckyCharm.LOGGER.info("Registering Perk Recipe Serializer");
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return PERK_TRIM_SERIALIZER;
    }
    @Override
    public boolean isEmpty() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::isEmpty);
    }
    public static class Serializer implements RecipeSerializer<PerkTrimRecipe> {
        private static final MapCodec<PerkTrimRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition)
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
            Ingredient ingredient = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredient2 = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredient3 = Ingredient.PACKET_CODEC.decode(buf);
            return new PerkTrimRecipe(ingredient, ingredient2, ingredient3);
        }
        private static void write(RegistryByteBuf buf, PerkTrimRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.template);
            Ingredient.PACKET_CODEC.encode(buf, recipe.base);
            Ingredient.PACKET_CODEC.encode(buf, recipe.addition);
        }
    }
    public static class PerkTrimRecipeJsonBuilder {
        private final RecipeCategory category;
        private final Ingredient template;
        private final Ingredient base;
        private final Ingredient addition;
        private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();
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
        public void offerTo(RecipeExporter exporter, Identifier recipeId) {
            this.validate(recipeId);
            Advancement.Builder builder = exporter.getAdvancementBuilder()
                    .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                    .rewards(AdvancementRewards.Builder.recipe(recipeId))
                    .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
            this.criteria.forEach(builder::criterion);
            PerkTrimRecipe perkTrimRecipe = new PerkTrimRecipe(this.template, this.base, this.addition);
            exporter.accept(recipeId, perkTrimRecipe, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")));
        }
        private void validate(Identifier recipeId) {
            if (this.criteria.isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + recipeId);
            }
        }
    }
}
