package salek664.lucky_charm.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootChoice;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.LootPoolEntry;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.loot.math.HyperLootMath;
import salek664.lucky_charm.util.HyperLootPoolDuck;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Debug(export = true)
@Mixin(LootPool.class)
public abstract class LootPoolMixin implements HyperLootPoolDuck {
    @Unique
    public FloatArrayList positiveDelta = FloatArrayList.of();
    @Unique
    public FloatArrayList negativeDelta = FloatArrayList.of();
    @Unique
    public int qualDotWeight;
    @Unique
    public float qualDotDeltaPositive;
    @Unique
    public float qualDotDeltaNegative;
    @Unique
    public int weightSum;
    @Unique
    public int maxQuality;
    @Unique
    public int minQuality;
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;",
                    remap = false
            )
    )
    private static Codec<LootPool> modifyCodec(Codec<LootPool> original) {
        return Codec.either(
            original,
            RecordCodecBuilder.<LootPool>create(
                instance -> instance.group(
                    MapCodec.assumeMapUnsafe(original).forGetter(Function.identity()),
                    Codec.FLOAT.listOf().fieldOf("positive_delta").forGetter(pool -> ((HyperLootPoolDuck) pool).getPositiveDelta()),
                    Codec.FLOAT.listOf().fieldOf("negative_delta").forGetter(pool -> ((HyperLootPoolDuck) pool).getNegativeDelta()),
                    Codec.FLOAT.fieldOf("qual_dot_delta+").forGetter(pool -> ((HyperLootPoolDuck) pool).getQualDotDeltaPositive()),
                    Codec.FLOAT.fieldOf("qual_dot_delta-").forGetter(pool -> ((HyperLootPoolDuck) pool).getQualDotDeltaPositive()),
                    Codec.INT.fieldOf("qual_dot_weight").forGetter(pool -> ((HyperLootPoolDuck) pool).getQualDotWeight()),
                    Codec.INT.fieldOf("weight_sum").forGetter(pool -> ((HyperLootPoolDuck) pool).getWeightSum()),
                    Codec.INT.fieldOf("max_quality").forGetter(pool -> ((HyperLootPoolDuck) pool).getMaxQuality()),
                    Codec.INT.fieldOf("min_quality").forGetter(pool -> ((HyperLootPoolDuck) pool).getMinQuality())
                ).apply(instance, (o, pd, nd, m, n, w, s, x, u) -> o)
            )
        ).comapFlatMap(
            either -> either.left()
                .or(either::right)
                .map(DataResult::success)
                .orElseGet(
                    () -> DataResult.error(() -> "No Codec found for LootPool")
                ),
            Either::left
        );
    }
    @Unique
    @Override
    public FloatArrayList getPositiveDelta() {
        return positiveDelta;
    }
    @Unique
    @Override
    public void setPositiveDelta(FloatArrayList positiveDelta) {
        this.positiveDelta = positiveDelta;
    }
    @Unique
    @Override
    public FloatArrayList getNegativeDelta() {
        return negativeDelta;
    }
    @Unique
    @Override
    public void setNegativeDelta(FloatArrayList negativeDelta) {
        this.negativeDelta = negativeDelta;
    }
    @Unique
    @Override
    public int getQualDotWeight() {
        return qualDotWeight;
    }
    @Unique
    @Override
    public void setQualDotWeight(int qualDotWeight) {
        this.qualDotWeight = qualDotWeight;
    }
    @Unique
    @Override
    public float getQualDotDeltaPositive() {
        return qualDotDeltaPositive;
    }
    @Unique
    @Override
    public void setQualDotDeltaPositive(float qualDotDeltaPositive) {
        this.qualDotDeltaPositive = qualDotDeltaPositive;
    }
    @Unique
    @Override
    public float getQualDotDeltaNegative() {
        return qualDotDeltaNegative;
    }
    @Unique
    @Override
    public void setQualDotDeltaNegative(float qualDotDeltaNegative) {
        this.qualDotDeltaNegative = qualDotDeltaNegative;
    }
    @Unique
    @Override
    public int getWeightSum() {
        return weightSum;
    }
    @Unique
    @Override
    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }
    @Unique
    @Override
    public int getMaxQuality() {
        return maxQuality;
    }
    @Unique
    @Override
    public void setMaxQuality(int maxQuality) {
        this.maxQuality = maxQuality;
    }
    @Unique
    @Override
    public int getMinQuality() {
        return minQuality;
    }
    @Unique
    @Override
    public void setMinQuality(int minQuality) {
        this.minQuality = minQuality;
    }
    @Unique
    public float luckExpectedValue(float luck) {
        float expectedValue = weightSum != 0 ? (float) qualDotWeight / weightSum : 0;
        if (luck == 0) return expectedValue;
        double expLuck = Math.exp((double) (-1 * luck) / 5);
        double Q = maxQuality - minQuality; double E = expectedValue - minQuality;
        double C = Q / E - 1;
        double val1 = 1 / (1 + C * expLuck);
        return (float) (Q * val1) + minQuality;
    }
    @Unique
    public float kScalingFactor(float luckExpectedValue, float luck) {
        float v1 = weightSum != 0 ? (float) qualDotWeight / weightSum : 0;
        float dotProduct = luck > 0 ? qualDotDeltaPositive : ((luck < 0) ? qualDotDeltaNegative : 0);
        return dotProduct != 0 ? (luckExpectedValue - v1) / dotProduct : 0;
    }
    @Unique
    private int calculateNewWeight(float luck, int prevWeight, int counter, float kFactor) {
        if (luck != 0 && positiveDelta != null && negativeDelta != null) {
            float delta = 0;
            if (luck > 0 && positiveDelta.size() > counter) {
                delta = positiveDelta.getFloat(counter);
            } else if (luck < 0 && negativeDelta.size() > counter) {
                delta = negativeDelta.getFloat(counter);
            }
            //LuckyCharm.LOGGER.info("Delta (" + counter + "): " + delta);
            double newProb = weightSum != 0 ? (double) prevWeight / weightSum + kFactor * delta : 0;
            newProb = Math.clamp(newProb, 0, 1);
            //LuckyCharm.LOGGER.info("New Prob: " + newProb);
            return (int) Math.floor(newProb * 100);
        }
        return prevWeight;
    }
    @Inject(
            method = "supplyOnce(Ljava/util/function/Consumer;Lnet/minecraft/loot/context/LootContext;)V",
            at = @At("HEAD")
    )
    private void initializeVariablesForCalculations(Consumer<ItemStack> consumer, LootContext context, CallbackInfo ci,
                                              @Share("kFactor") LocalFloatRef kFactorRef,
                                              @Share("counterForWeightCalculation") LocalIntRef counterRef,
                                              @Share("counterForLootGeneration") LocalIntRef generationCounterRef,
                                              @Share("weightsListForLootGeneration") LocalRef<IntArrayList> weightsListRef) {
        float luck = context.getLuck();
        float eV = luckExpectedValue(luck);
        kFactorRef.set(kScalingFactor(eV, luck));
        counterRef.set(-1);
        generationCounterRef.set(-1);
        weightsListRef.set(IntArrayList.of());
        //LuckyCharm.LOGGER.info("Luck ev: " + eV);
        //LuckyCharm.LOGGER.info("Luck delta: " + ((luck > 0) ? positiveDelta : ((luck < 0) ? negativeDelta : 0)));
        //LuckyCharm.LOGGER.info("Qual dot delta: " + ((luck > 0) ? qualDotDeltaPositive : ((luck < 0) ? qualDotDeltaNegative : 0)));
        //LuckyCharm.LOGGER.info("Qual dot weight: " + qualDotWeight);
        //LuckyCharm.LOGGER.info("delta scaling factor: " + kFactorRef.get());
    }
    @ModifyVariable(
            method = "supplyOnce(Ljava/util/function/Consumer;Lnet/minecraft/loot/context/LootContext;)V",
            at = @At(value = "LOAD", ordinal = 0),
            allow = 1
    )
    private Iterator<List<LootPoolEntry>> countAndSaveLuckDelta(Iterator<List<LootPoolEntry>> iterator,
                                                                @Share("counterForWeightCalculation") LocalIntRef counterRef) {
        counterRef.set(counterRef.get() + 1);
        return iterator;
    }
    @WrapOperation(
            method = "supplyOnce(Ljava/util/function/Consumer;Lnet/minecraft/loot/context/LootContext;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/entry/LootPoolEntry;expand(Lnet/minecraft/loot/context/LootContext;Ljava/util/function/Consumer;)Z")
    )
    private boolean modifyExpand(LootPoolEntry instance, LootContext context, Consumer<LootChoice> lootConsumer, Operation<Boolean> original,
                                 @Share("kFactor") LocalFloatRef kFactorRef,
                                 @Share("counterForWeightCalculation") LocalIntRef counterRef,
                                 @Share("weightsListForLootGeneration") LocalRef<IntArrayList> weightsListRef,
                                 @Local List<LootChoice> list,
                                 @Local MutableInt mutableInt) {
        return instance.expand(context, choice -> {
            int newWeight = calculateNewWeight(context.getLuck(), choice.getWeight(0), counterRef.get(), kFactorRef.get());
            //LuckyCharm.LOGGER.info("/hyper: " + newWeight);
            if (newWeight > 0) {
                list.add(choice);
                mutableInt.add(newWeight);
                weightsListRef.get().add(newWeight);
            }
        });
    }
    @ModifyVariable(
            method = "supplyOnce(Ljava/util/function/Consumer;Lnet/minecraft/loot/context/LootContext;)V",
            at = @At(value = "LOAD", ordinal = 2),
            allow = 1
    )
    private Iterator<List<LootChoice>> countForLootGeneration(Iterator<List<LootChoice>> iterator,
                                                                 @Share("counterForLootGeneration") LocalIntRef generationCounterRef) {
        generationCounterRef.set(generationCounterRef.get() + 1);
        return iterator;
    }
    @WrapOperation(
            method = "supplyOnce(Ljava/util/function/Consumer;Lnet/minecraft/loot/context/LootContext;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/LootChoice;getWeight(F)I")
    )
    private int modifyWeight(LootChoice lootChoice, float luck, Operation<Integer> original,
                             @Share("kFactor") LocalFloatRef kFactorRef,
                             @Share("counterForLootGeneration") LocalIntRef counterRef,
                             @Share("weightsListForLootGeneration") LocalRef<IntArrayList> weightsListRef) {
        IntArrayList weightsList = weightsListRef.get();
        int i = counterRef.get();
        return weightsList.size() > i ? weightsList.getInt(i) : original.call(lootChoice, 0F);
    }
}
