package salek664.lucky_charm.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.loot.LootTable;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import salek664.lucky_charm.config.LuckyCharmConfigReader;

import java.util.Optional;

public class LuckLootContainerCriterion extends AbstractCriterion<LuckLootContainerCriterion.Conditions> {
    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }
    public void trigger(ServerPlayerEntity player, RegistryKey<LootTable> lootTable, float luck) {
        super.trigger(player, conditions -> conditions.test(lootTable, luck));
    }
    public record Conditions(Optional<LootContextPredicate> player, float luck) implements AbstractCriterion.Conditions {
        public static final Codec<LuckLootContainerCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(LuckLootContainerCriterion.Conditions::player),
                                Codec.FLOAT.fieldOf("luck").forGetter(LuckLootContainerCriterion.Conditions::luck)
                        )
                        .apply(instance, LuckLootContainerCriterion.Conditions::new)
        );
        public static AdvancementCriterion<LuckLootContainerCriterion.Conditions> create(float luck) {
            return LuckyCharmCriteria.PLAYER_OPENS_CHEST_WITH_LUCK.create(new LuckLootContainerCriterion.Conditions(Optional.empty(), luck));
        }
        public boolean test(RegistryKey<LootTable> lootTable, float luck) {
            boolean isExcept = false;
            for (String except : LuckyCharmConfigReader.getExcepts()) {
                if (lootTable.getValue().toUnderscoreSeparatedString().endsWith(except)) {
                    isExcept = true;
                    break;
                }
            }
            return this.luck <= luck && !isExcept;
        }
    }
}
