package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement14Stack extends ItemStack {
    ItemType<ItemElement14Stack> ELEMENT_14_TYPE = ItemTypeBuilder
            .builder(ItemElement14Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_14)
            .build();
}
