package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCameraStack extends ItemStack {
    ItemType<ItemItemCameraStack> ITEM_CAMERA_TYPE = ItemTypeBuilder
            .builder(ItemItemCameraStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAMERA)
            .build();
}
