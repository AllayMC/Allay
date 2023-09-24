package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement63Stack extends ItemStack {
    ItemType<ItemElement63Stack> ELEMENT_63_TYPE = ItemTypeBuilder
            .builder(ItemElement63Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_63)
            .build();
}
