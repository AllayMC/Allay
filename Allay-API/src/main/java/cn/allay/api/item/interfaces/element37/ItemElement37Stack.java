package cn.allay.api.item.interfaces.element37;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement37Stack extends ItemStack {
    ItemType<ItemElement37Stack> ELEMENT_37_TYPE = ItemTypeBuilder
            .builder(ItemElement37Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_37)
            .build();
}
