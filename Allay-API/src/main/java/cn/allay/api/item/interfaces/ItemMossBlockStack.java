package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossBlockStack extends ItemStack {
    ItemType<ItemMossBlockStack> MOSS_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemMossBlockStack.class)
            .vanillaItem(VanillaItemId.MOSS_BLOCK)
            .build();
}
