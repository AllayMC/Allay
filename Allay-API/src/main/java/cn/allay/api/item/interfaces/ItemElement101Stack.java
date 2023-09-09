package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement101Stack extends ItemStack {
    ItemType<ItemElement101Stack> ELEMENT_101_TYPE = ItemTypeBuilder
            .builder(ItemElement101Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_101)
            .build();
}
