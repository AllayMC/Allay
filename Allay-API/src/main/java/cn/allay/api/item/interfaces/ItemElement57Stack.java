package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement57Stack extends ItemStack {
    ItemType<ItemElement57Stack> ELEMENT_57_TYPE = ItemTypeBuilder
            .builder(ItemElement57Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_57)
            .build();
}
