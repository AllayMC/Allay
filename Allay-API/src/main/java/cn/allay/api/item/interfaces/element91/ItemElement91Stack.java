package cn.allay.api.item.interfaces.element91;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement91Stack extends ItemStack {
    ItemType<ItemElement91Stack> ELEMENT_91_TYPE = ItemTypeBuilder
            .builder(ItemElement91Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_91)
            .build();
}
