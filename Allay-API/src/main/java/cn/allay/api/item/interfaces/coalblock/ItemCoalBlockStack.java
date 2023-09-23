package cn.allay.api.item.interfaces.coalblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalBlockStack extends ItemStack {
    ItemType<ItemCoalBlockStack> COAL_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemCoalBlockStack.class)
            .vanillaItem(VanillaItemId.COAL_BLOCK)
            .build();
}
