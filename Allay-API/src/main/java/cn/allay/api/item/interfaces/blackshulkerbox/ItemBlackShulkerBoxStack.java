package cn.allay.api.item.interfaces.blackshulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackShulkerBoxStack extends ItemStack {
    ItemType<ItemBlackShulkerBoxStack> BLACK_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemBlackShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.BLACK_SHULKER_BOX)
            .build();
}
