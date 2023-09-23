package cn.allay.api.item.interfaces.element84;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement84Stack extends ItemStack {
    ItemType<ItemElement84Stack> ELEMENT_84_TYPE = ItemTypeBuilder
            .builder(ItemElement84Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_84)
            .build();
}
