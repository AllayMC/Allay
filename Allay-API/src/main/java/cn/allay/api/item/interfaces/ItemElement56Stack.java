package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement56Stack extends ItemStack {
    ItemType<ItemElement56Stack> ELEMENT_56_TYPE = ItemTypeBuilder
            .builder(ItemElement56Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_56)
            .build();
}
