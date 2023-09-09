package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement17Stack extends ItemStack {
    ItemType<ItemElement17Stack> ELEMENT_17_TYPE = ItemTypeBuilder
            .builder(ItemElement17Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_17)
            .build();
}
