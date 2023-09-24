package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpurBlockStack extends ItemStack {
    ItemType<ItemPurpurBlockStack> PURPUR_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemPurpurBlockStack.class)
            .vanillaItem(VanillaItemId.PURPUR_BLOCK)
            .build();
}
