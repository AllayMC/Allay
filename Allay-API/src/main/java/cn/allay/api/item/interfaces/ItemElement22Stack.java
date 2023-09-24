package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement22Stack extends ItemStack {
    ItemType<ItemElement22Stack> ELEMENT_22_TYPE = ItemTypeBuilder
            .builder(ItemElement22Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_22)
            .build();
}
