package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement61Stack extends ItemStack {
    ItemType<ItemElement61Stack> ELEMENT_61_TYPE = ItemTypeBuilder
            .builder(ItemElement61Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_61)
            .build();
}
