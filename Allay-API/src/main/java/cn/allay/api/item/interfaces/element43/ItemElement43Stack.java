package cn.allay.api.item.interfaces.element43;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement43Stack extends ItemStack {
    ItemType<ItemElement43Stack> ELEMENT_43_TYPE = ItemTypeBuilder
            .builder(ItemElement43Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_43)
            .build();
}
