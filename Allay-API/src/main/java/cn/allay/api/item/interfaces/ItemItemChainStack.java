package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemChainStack extends ItemStack {
    ItemType<ItemItemChainStack> ITEM_CHAIN_TYPE = ItemTypeBuilder
            .builder(ItemItemChainStack.class)
            .vanillaItem(VanillaItemId.ITEM_CHAIN)
            .build();
}
