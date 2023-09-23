package cn.allay.api.item.interfaces.element10;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement10Stack extends ItemStack {
    ItemType<ItemElement10Stack> ELEMENT_10_TYPE = ItemTypeBuilder
            .builder(ItemElement10Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_10)
            .build();
}
