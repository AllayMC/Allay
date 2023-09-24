package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement118Stack extends ItemStack {
    ItemType<ItemElement118Stack> ELEMENT_118_TYPE = ItemTypeBuilder
            .builder(ItemElement118Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_118)
            .build();
}
