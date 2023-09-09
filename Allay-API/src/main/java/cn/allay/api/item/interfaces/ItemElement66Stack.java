package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement66Stack extends ItemStack {
    ItemType<ItemElement66Stack> ELEMENT_66_TYPE = ItemTypeBuilder
            .builder(ItemElement66Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_66)
            .build();
}
