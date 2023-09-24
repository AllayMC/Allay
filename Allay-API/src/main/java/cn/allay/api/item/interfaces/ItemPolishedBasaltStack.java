package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBasaltStack extends ItemStack {
    ItemType<ItemPolishedBasaltStack> POLISHED_BASALT_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBasaltStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BASALT)
            .build();
}
