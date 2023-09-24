package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawIronBlockStack extends ItemStack {
    ItemType<ItemRawIronBlockStack> RAW_IRON_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRawIronBlockStack.class)
            .vanillaItem(VanillaItemId.RAW_IRON_BLOCK)
            .build();
}
