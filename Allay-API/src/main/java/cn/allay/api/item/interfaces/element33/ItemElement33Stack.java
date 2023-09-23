package cn.allay.api.item.interfaces.element33;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement33Stack extends ItemStack {
    ItemType<ItemElement33Stack> ELEMENT_33_TYPE = ItemTypeBuilder
            .builder(ItemElement33Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_33)
            .build();
}
