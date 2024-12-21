package salek664.lucky_charm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.advancement.criterion.LuckLootContainerCriterion;
import salek664.lucky_charm.item.LuckyCharmItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LuckyCharmAdvancementProvider extends FabricAdvancementProvider {
    public LuckyCharmAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }
    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry a = Advancement.Builder.create()
                .display(
                        LuckyCharmItems.FOURLEAF_CLOVER,
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.get_lucky.title"))
                        ),
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.get_lucky.description"))
                        ),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("lucky_loot", LuckLootContainerCriterion.Conditions.create(3))
                .build(consumer, "adventure/get_lucky");
        Advancement.Builder.create()
                .display(
                        LuckyCharmItems.FORTUNE_GEM,
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.extreme_luck.title"))
                        ),
                        Text.translatable(
                                Util.createTranslationKey("advancements", new Identifier(LuckyCharm.MOD_ID, "adventure.extreme_luck.description"))
                        ),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .parent(a)
                .criterion("extreme_luck", LuckLootContainerCriterion.Conditions.create(7))
                .build(consumer, "adventure/extreme_luck");
    }
}
