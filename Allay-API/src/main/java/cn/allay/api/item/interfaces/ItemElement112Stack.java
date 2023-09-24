package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement112Stack extends ItemStack {
    ItemType<ItemElement112Stack> ELEMENT_112_TYPE = ItemTypeBuilder
            .builder(ItemElement112Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_112)
            .build();
}
