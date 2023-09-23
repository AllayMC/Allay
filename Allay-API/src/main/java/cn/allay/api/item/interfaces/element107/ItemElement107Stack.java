package cn.allay.api.item.interfaces.element107;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement107Stack extends ItemStack {
    ItemType<ItemElement107Stack> ELEMENT_107_TYPE = ItemTypeBuilder
            .builder(ItemElement107Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_107)
            .build();
}
