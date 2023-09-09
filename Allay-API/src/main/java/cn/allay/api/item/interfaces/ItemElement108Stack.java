package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement108Stack extends ItemStack {
    ItemType<ItemElement108Stack> ELEMENT_108_TYPE = ItemTypeBuilder
            .builder(ItemElement108Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_108)
            .build();
}
