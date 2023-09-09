package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement39Stack extends ItemStack {
    ItemType<ItemElement39Stack> ELEMENT_39_TYPE = ItemTypeBuilder
            .builder(ItemElement39Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_39)
            .build();
}
