package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement19Stack extends ItemStack {
    ItemType<ItemElement19Stack> ELEMENT_19_TYPE = ItemTypeBuilder
            .builder(ItemElement19Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_19)
            .build();
}
