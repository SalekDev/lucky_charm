package salek664.lucky_charm.mixin.loot;

import net.minecraft.loot.entry.LeafEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LeafEntry.class)
public interface LeafEntryAccessor {
    @Accessor("quality")
    int getQuality();
    @Accessor("weight")
    int getWeight();
}
