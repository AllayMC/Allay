package cn.allay.api.item.interfaces.element75;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement75Stack extends ItemStack {
    ItemType<ItemElement75Stack> ELEMENT_75_TYPE = ItemTypeBuilder
            .builder(ItemElement75Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_75)
            .build();
}
