package cn.allay.api.item.interfaces.undyedshulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUndyedShulkerBoxStack extends ItemStack {
    ItemType<ItemUndyedShulkerBoxStack> UNDYED_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemUndyedShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.UNDYED_SHULKER_BOX)
            .build();
}
