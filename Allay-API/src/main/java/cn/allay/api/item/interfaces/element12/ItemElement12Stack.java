package cn.allay.api.item.interfaces.element12;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement12Stack extends ItemStack {
    ItemType<ItemElement12Stack> ELEMENT_12_TYPE = ItemTypeBuilder
            .builder(ItemElement12Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_12)
            .build();
}
