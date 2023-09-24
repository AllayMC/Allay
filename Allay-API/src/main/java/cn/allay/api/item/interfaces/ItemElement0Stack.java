package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement0Stack extends ItemStack {
    ItemType<ItemElement0Stack> ELEMENT_0_TYPE = ItemTypeBuilder
            .builder(ItemElement0Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_0)
            .build();
}
