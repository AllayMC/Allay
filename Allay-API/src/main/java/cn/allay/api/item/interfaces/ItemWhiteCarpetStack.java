package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCarpetStack extends ItemStack {
    ItemType<ItemWhiteCarpetStack> WHITE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemWhiteCarpetStack.class)
            .vanillaItem(VanillaItemId.WHITE_CARPET)
            .build();
}
