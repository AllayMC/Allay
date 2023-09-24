package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement49Stack extends ItemStack {
    ItemType<ItemElement49Stack> ELEMENT_49_TYPE = ItemTypeBuilder
            .builder(ItemElement49Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_49)
            .build();
}
