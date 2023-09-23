package cn.allay.api.item.interfaces.element29;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement29Stack extends ItemStack {
    ItemType<ItemElement29Stack> ELEMENT_29_TYPE = ItemTypeBuilder
            .builder(ItemElement29Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_29)
            .build();
}
