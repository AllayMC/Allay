package cn.allay.api.item.interfaces.itemglowframe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemGlowFrameStack extends ItemStack {
    ItemType<ItemItemGlowFrameStack> ITEM_GLOW_FRAME_TYPE = ItemTypeBuilder
            .builder(ItemItemGlowFrameStack.class)
            .vanillaItem(VanillaItemId.ITEM_GLOW_FRAME)
            .build();
}
