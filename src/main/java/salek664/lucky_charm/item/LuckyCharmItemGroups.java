package salek664.lucky_charm.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import salek664.lucky_charm.LuckyCharm;

public class LuckyCharmItemGroups {
    public static final ItemGroup MAIN = Registry.register(Registries.ITEM_GROUP, new Identifier(LuckyCharm.MOD_ID, "lucky_charm"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lucky_charm"))
                    .icon(() -> new ItemStack(LuckyCharmItems.FOURLEAF_CLOVER)).entries(((displayContext, entries) -> {
                        entries.add(LuckyCharmItems.FOURLEAF_CLOVER);
                        entries.add(LuckyCharmItems.THREELEAF_CLOVER);
                        entries.add(LuckyCharmItems.LUCKY_CHARM_BOWL);
                        entries.add(LuckyCharmItems.FORTUNED_TRAVELLER_SMITHING_TEMPLATE);
                        entries.add(LuckyCharmItems.FORTUNE_GEM);
                    })).build());

    public static void registerItemGroups() {
        LuckyCharm.LOGGER.info("Registering Item Groups for " + LuckyCharm.MOD_ID);
    }
}
