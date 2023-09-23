package cn.allay.api.item.interfaces.blackcarpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCarpetStack extends ItemStack {
    ItemType<ItemBlackCarpetStack> BLACK_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemBlackCarpetStack.class)
            .vanillaItem(VanillaItemId.BLACK_CARPET)
            .build();
}
