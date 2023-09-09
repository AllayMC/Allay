package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement60Stack extends ItemStack {
    ItemType<ItemElement60Stack> ELEMENT_60_TYPE = ItemTypeBuilder
            .builder(ItemElement60Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_60)
            .build();
}
