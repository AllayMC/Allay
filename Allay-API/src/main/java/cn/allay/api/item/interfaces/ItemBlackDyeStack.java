package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackDyeStack extends ItemStack {
    ItemType<ItemBlackDyeStack> BLACK_DYE_TYPE = ItemTypeBuilder
            .builder(ItemBlackDyeStack.class)
            .vanillaItem(VanillaItemId.BLACK_DYE)
            .build();
}
