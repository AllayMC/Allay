package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchFenceStack extends ItemStack {
    ItemType<ItemBirchFenceStack> BIRCH_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemBirchFenceStack.class)
            .vanillaItem(VanillaItemId.BIRCH_FENCE)
            .build();
}
