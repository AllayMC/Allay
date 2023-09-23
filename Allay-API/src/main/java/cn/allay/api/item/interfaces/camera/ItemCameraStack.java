package cn.allay.api.item.interfaces.camera;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCameraStack extends ItemStack {
    ItemType<ItemCameraStack> CAMERA_TYPE = ItemTypeBuilder
            .builder(ItemCameraStack.class)
            .vanillaItem(VanillaItemId.CAMERA)
            .build();
}
