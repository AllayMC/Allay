package cn.allay.api.item.interfaces.frame;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrameStack extends ItemStack {
    ItemType<ItemFrameStack> FRAME_TYPE = ItemTypeBuilder
            .builder(ItemFrameStack.class)
            .vanillaItem(VanillaItemId.FRAME)
            .build();
}
