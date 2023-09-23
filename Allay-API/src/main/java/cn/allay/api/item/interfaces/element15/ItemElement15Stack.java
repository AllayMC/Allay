package cn.allay.api.item.interfaces.element15;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement15Stack extends ItemStack {
    ItemType<ItemElement15Stack> ELEMENT_15_TYPE = ItemTypeBuilder
            .builder(ItemElement15Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_15)
            .build();
}
