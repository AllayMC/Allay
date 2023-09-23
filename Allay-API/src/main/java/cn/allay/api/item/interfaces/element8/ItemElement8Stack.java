package cn.allay.api.item.interfaces.element8;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement8Stack extends ItemStack {
    ItemType<ItemElement8Stack> ELEMENT_8_TYPE = ItemTypeBuilder
            .builder(ItemElement8Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_8)
            .build();
}
