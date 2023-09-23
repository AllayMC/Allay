package cn.allay.api.item.interfaces.element41;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement41Stack extends ItemStack {
    ItemType<ItemElement41Stack> ELEMENT_41_TYPE = ItemTypeBuilder
            .builder(ItemElement41Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_41)
            .build();
}
