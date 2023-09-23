package cn.allay.api.item.interfaces.element35;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement35Stack extends ItemStack {
    ItemType<ItemElement35Stack> ELEMENT_35_TYPE = ItemTypeBuilder
            .builder(ItemElement35Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_35)
            .build();
}
