package salek664.lucky_charm.advancement.criterion;

import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

public class LuckyCharmCriteria {
    public static final LuckLootContainerCriterion PLAYER_OPENS_CHEST_WITH_LUCK = register("player_opens_chest_with_luck",
            new LuckLootContainerCriterion());
    public static <T extends Criterion<?>> T register(String id, T criterion) {
        return Registry.register(Registries.CRITERION, new Identifier(LuckyCharm.MOD_ID, id), criterion);
    }
    public static void registerCriteria() {
        LuckyCharm.LOGGER.info("Registering advancements criteria for " + LuckyCharm.MOD_ID);
    }
}
