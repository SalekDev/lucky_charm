package salek664.lucky_charm.loot.hyper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootChoice;
import net.minecraft.loot.LootTableReporter;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionConsumingBuilder;
import net.minecraft.loot.condition.LootConditionTypes;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootPoolEntryTypes;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionConsumingBuilder;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;
import org.apache.commons.lang3.mutable.MutableInt;
import org.ejml.UtilEjml;
import salek664.lucky_charm.loot.math.HyperLootMath;
import salek664.lucky_charm.mixin.LeafEntryAccessor;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HyperLootPool {
    public static final Codec<HyperLootPool> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            LootPoolEntryTypes.CODEC.listOf().fieldOf("entries").forGetter(pool -> pool.entries),
                            LootConditionTypes.CODEC.listOf().optionalFieldOf("conditions", List.of()).forGetter(pool -> pool.conditions),
                            LootFunctionTypes.CODEC.listOf().optionalFieldOf("functions", List.of()).forGetter(pool -> pool.functions),
                            LootNumberProviderTypes.CODEC.fieldOf("rolls").forGetter(pool -> pool.rolls),
                            Codec.FLOAT.listOf().fieldOf("luck_delta").forGetter(pool -> new FloatArrayList(pool.luckDelta)),
                            Codec.FLOAT.fieldOf("qual_dot_weight").forGetter(pool -> pool.qualDotWeight),
                            Codec.FLOAT.fieldOf("qual_dot_luck").forGetter(pool -> pool.qualDotLuckD),
                            Codec.FLOAT.fieldOf("weight_sum").forGetter(pool -> pool.weightSum),
                            Codec.INT.fieldOf("max_quality").forGetter(pool -> pool.maxQuality)
                    )
                    .apply(instance, HyperLootPool::new)
    );
    public final List<LootPoolEntry> entries;
    public final List<LootCondition> conditions;
    private final Predicate<LootContext> predicate;
    private final List<LootFunction> functions;
    private final BiFunction<ItemStack, LootContext, ItemStack> javaFunctions;
    public final LootNumberProvider rolls;
    public final float[] luckDelta;
    public final float qualDotWeight;
    public final float qualDotLuckD;
    public final float weightSum;
    public final int maxQuality;
    private HyperLootPool(List<LootPoolEntry> entries, List<LootCondition> conditions, List<LootFunction> functions, LootNumberProvider rolls,
                          List<Float> luckDelta, float qualDotWeight, float qualDotLuckD, float weightSum, int maxQuality) {
        this(entries, conditions, functions, rolls, (new FloatArrayList(luckDelta)).toArray(new float[0]), qualDotWeight, qualDotWeight,
                weightSum, maxQuality);
    }
    private HyperLootPool(List<LootPoolEntry> entries, List<LootCondition> conditions, List<LootFunction> functions, LootNumberProvider rolls,
                          float[] luckDelta, float qualDotWeight, float qualDotLuckD, float weightSum, int maxQuality) {
        this.entries = entries;
        this.conditions = conditions;
        this.predicate = Util.allOf(conditions);
        this.functions = functions;
        this.javaFunctions = LootFunctionTypes.join(functions);
        this.rolls = rolls;
        this.luckDelta = luckDelta;
        this.qualDotWeight = qualDotWeight;
        this.qualDotLuckD = qualDotLuckD;
        this.weightSum = weightSum;
        this.maxQuality = maxQuality;
    }
    private void supplyOnce(Consumer<ItemStack> lootConsumer, LootContext context) {
        Random random = context.getRandom();
        List<LootChoice> list = Lists.<LootChoice>newArrayList();
        MutableInt mutableInt = new MutableInt();
        float luck = context.getLuck();
        float eV = luckExpectedValue(luck);
        float kFactor = deltaScalingFactor(eV);
        for (int k = 0; k < entries.size(); k++) {
            int finalK = k;
            entries.get(k).expand(context, choice -> {
                int i = choice.getWeight(0);
                double newProb = i / weightSum + kFactor * luckDelta[finalK];
                int newWeight = (int) Math.floor(newProb * 100);
                if (newWeight > 0) {
                    list.add(choice);
                    mutableInt.add(newWeight);
                }
            });
        }
        int i = list.size();
        if (mutableInt.intValue() != 0 && i != 0) {
            if (i == 1) {
                (list.get(0)).generateLoot(lootConsumer, context);
            } else {
                int j = random.nextInt(mutableInt.intValue());
                for (LootChoice lootChoice : list) {
                    j -= lootChoice.getWeight(context.getLuck());
                    if (j < 0) {
                        lootChoice.generateLoot(lootConsumer, context);
                        return;
                    }
                }
            }
        }
    }
    public void addGeneratedLoot(Consumer<ItemStack> lootConsumer, LootContext context) {
        if (this.predicate.test(context)) {
            Consumer<ItemStack> consumer = LootFunction.apply(this.javaFunctions, lootConsumer, context);
            int i = this.rolls.nextInt(context);

            for (int j = 0; j < i; j++) {
                this.supplyOnce(consumer, context);
            }
        }
    }
    public void validate(LootTableReporter reporter) {
        for (int i = 0; i < this.conditions.size(); i++) {
            (this.conditions.get(i)).validate(reporter.makeChild(".condition[" + i + "]"));
        }
        for (int i = 0; i < this.functions.size(); i++) {
            (this.functions.get(i)).validate(reporter.makeChild(".functions[" + i + "]"));
        }
        for (int i = 0; i < this.entries.size(); i++) {
            (this.entries.get(i)).validate(reporter.makeChild(".entries[" + i + "]"));
        }
        this.rolls.validate(reporter.makeChild(".rolls"));
    }
    public static HyperLootPool.Builder builder() {
        return new HyperLootPool.Builder();
    }
    public static float[] calculateLuckDelta(float[] qVector) {
        int N = qVector.length;
        switch (N) {
            case 0, 1, 2 -> {
                return UtilEjml.ZERO_LENGTH_F32;
            }
            default -> {
                float[] result = new float[N];
                float[][] solutionSpace = new float[N - 1][N];
                //Calculate solution vectors
                solutionSpace[0][N - 1] = 1;
                solutionSpace[0][N - 2] = 1;
                for (int i = 1; i < N - 1; i++) {
                    solutionSpace[0][i - 1] = 1;
                    solutionSpace[i][i - 1] = qVector[N - 2] - qVector[N - 1];
                    solutionSpace[i][N - 1] = qVector[i - 1] - qVector[N - 2];
                    solutionSpace[i][N - 2] = qVector[N - 1] - qVector[i - 1];
                }
                HyperLootMath.generalizedCrossProduct(solutionSpace, result, N);
                return result;
            }
        }
    }
    public float luckExpectedValue(float luck) {
        float expectedValue = qualDotWeight / weightSum;
        if (luck == 0) return expectedValue;
        double expLuck = Math.exp((double) (-1 * luck) / 5);
        double val = 2 / (1 + expLuck);
        return (float) (luck < 0 ? expectedValue * val : expectedValue + (maxQuality - expectedValue) * val);
    }
    public float deltaScalingFactor(float expectedValue) {
        float v1 = qualDotWeight / weightSum;
        if (expectedValue == v1) return 0;
        return qualDotLuckD != 0 ? (expectedValue - v1) / qualDotLuckD : 0;
    }
    public static class Builder implements LootFunctionConsumingBuilder<HyperLootPool.Builder>, LootConditionConsumingBuilder<HyperLootPool.Builder> {
        private final ImmutableList.Builder<LootPoolEntry> entries = ImmutableList.builder();
        private final ImmutableList.Builder<LootCondition> conditions = ImmutableList.builder();
        private final ImmutableList.Builder<LootFunction> functions = ImmutableList.builder();
        private LootNumberProvider rolls = ConstantLootNumberProvider.create(1.0F);
        private final FloatArrayList qualities = FloatArrayList.of();
        private float qualDotWeight;
        private int weightSum;
        private int maxQuality;
        public HyperLootPool.Builder rolls(LootNumberProvider rolls) {
            this.rolls = rolls;
            return this;
        }
        public HyperLootPool.Builder getThisFunctionConsumingBuilder() {
            return this;
        }
        public HyperLootPool.Builder with(LootPoolEntry.Builder<?> entry) {
            LootPoolEntry e = entry.build();
            entries.add(e);
            if (e instanceof LeafEntry leafEntry) {
                int quality = ((LeafEntryAccessor)leafEntry).getQuality();
                int weight = ((LeafEntryAccessor)leafEntry).getWeight();
                qualities.add(quality);
                weightSum += weight;
                qualDotWeight += weight * quality;
                maxQuality = Math.max(maxQuality, quality);
            } else {
                qualities.add(0);
            }
            return this;
        }
        public HyperLootPool.Builder conditionally(LootCondition.Builder builder) {
            conditions.add(builder.build());
            return this;
        }
        @Override
        public HyperLootPool.Builder getThisConditionConsumingBuilder() {
            return this;
        }
        public HyperLootPool.Builder apply(LootFunction.Builder builder) {
            functions.add(builder.build());
            return this;
        }
        public HyperLootPool build() {
            float[] luckDelta = calculateLuckDelta(qualities.toArray(new float[0]));
            float qualDotLuck = 0;
            for (int i = 0; i < qualities.size(); i++) {
                qualDotLuck += qualities.getFloat(i) * luckDelta[i];
            }
            return new HyperLootPool(entries.build(), conditions.build(), functions.build(), rolls, luckDelta,
                    qualDotWeight, qualDotLuck, weightSum, maxQuality);
        }
    }
}