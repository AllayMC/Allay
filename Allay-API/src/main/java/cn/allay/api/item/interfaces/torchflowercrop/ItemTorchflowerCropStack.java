package cn.allay.api.item.interfaces.torchflowercrop;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchflowerCropStack extends ItemStack {
    ItemType<ItemTorchflowerCropStack> TORCHFLOWER_CROP_TYPE = ItemTypeBuilder
            .builder(ItemTorchflowerCropStack.class)
            .vanillaItem(VanillaItemId.TORCHFLOWER_CROP)
            .build();
}
