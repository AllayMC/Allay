package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawGoldBlockStack extends ItemStack {
    ItemType<ItemRawGoldBlockStack> RAW_GOLD_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRawGoldBlockStack.class)
            .vanillaItem(VanillaItemId.RAW_GOLD_BLOCK)
            .build();
}
