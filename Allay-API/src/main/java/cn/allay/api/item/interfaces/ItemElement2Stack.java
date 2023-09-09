package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement2Stack extends ItemStack {
    ItemType<ItemElement2Stack> ELEMENT_2_TYPE = ItemTypeBuilder
            .builder(ItemElement2Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_2)
            .build();
}
