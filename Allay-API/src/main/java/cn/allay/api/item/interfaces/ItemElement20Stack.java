package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement20Stack extends ItemStack {
    ItemType<ItemElement20Stack> ELEMENT_20_TYPE = ItemTypeBuilder
            .builder(ItemElement20Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_20)
            .build();
}
