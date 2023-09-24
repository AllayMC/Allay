package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownCarpetStack extends ItemStack {
    ItemType<ItemBrownCarpetStack> BROWN_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemBrownCarpetStack.class)
            .vanillaItem(VanillaItemId.BROWN_CARPET)
            .build();
}
