package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement28Stack extends ItemStack {
    ItemType<ItemElement28Stack> ELEMENT_28_TYPE = ItemTypeBuilder
            .builder(ItemElement28Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_28)
            .build();
}
