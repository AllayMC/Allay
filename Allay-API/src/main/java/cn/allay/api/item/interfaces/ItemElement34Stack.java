package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement34Stack extends ItemStack {
    ItemType<ItemElement34Stack> ELEMENT_34_TYPE = ItemTypeBuilder
            .builder(ItemElement34Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_34)
            .build();
}
