package cn.allay.api.item.interfaces.wheat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWheatStack extends ItemStack {
    ItemType<ItemWheatStack> WHEAT_TYPE = ItemTypeBuilder
            .builder(ItemWheatStack.class)
            .vanillaItem(VanillaItemId.WHEAT)
            .build();
}
