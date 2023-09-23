package cn.allay.api.item.interfaces.glowstick;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowStickStack extends ItemStack {
    ItemType<ItemGlowStickStack> GLOW_STICK_TYPE = ItemTypeBuilder
            .builder(ItemGlowStickStack.class)
            .vanillaItem(VanillaItemId.GLOW_STICK)
            .build();
}
