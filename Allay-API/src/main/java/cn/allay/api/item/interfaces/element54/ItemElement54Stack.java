package cn.allay.api.item.interfaces.element54;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement54Stack extends ItemStack {
    ItemType<ItemElement54Stack> ELEMENT_54_TYPE = ItemTypeBuilder
            .builder(ItemElement54Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_54)
            .build();
}
