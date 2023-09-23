package cn.allay.api.item.interfaces.element23;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement23Stack extends ItemStack {
    ItemType<ItemElement23Stack> ELEMENT_23_TYPE = ItemTypeBuilder
            .builder(ItemElement23Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_23)
            .build();
}
