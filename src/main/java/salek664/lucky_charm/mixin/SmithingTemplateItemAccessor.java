package salek664.lucky_charm.mixin;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public interface SmithingTemplateItemAccessor {
	@Accessor("ARMOR_TRIM_APPLIES_TO_TEXT")
	static Text getArmorTrimText() {
		throw new AssertionError();
	}
	@Accessor("ARMOR_TRIM_BASE_SLOT_DESCRIPTION_TEXT")
	static Text getArmorTrimBaseSlotDescriptionText() {
		throw new AssertionError();
	}
	@Invoker("getArmorTrimEmptyBaseSlotTextures")
	static List<Identifier> invokeGetArmorTrimBaseSlotTextures() {
		throw new AssertionError();
	}
	@Invoker("getArmorTrimEmptyAdditionsSlotTextures")
	static List<Identifier> invokeGetArmorAdditionsSlotTextures() {
		throw new AssertionError();
	}
}