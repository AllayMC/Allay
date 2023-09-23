package cn.allay.api.item.interfaces.element99;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement99Stack extends ItemStack {
    ItemType<ItemElement99Stack> ELEMENT_99_TYPE = ItemTypeBuilder
            .builder(ItemElement99Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_99)
            .build();
}
