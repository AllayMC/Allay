package cn.allay.api.item.interfaces.element1;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement1Stack extends ItemStack {
    ItemType<ItemElement1Stack> ELEMENT_1_TYPE = ItemTypeBuilder
            .builder(ItemElement1Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_1)
            .build();
}
