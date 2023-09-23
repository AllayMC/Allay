package cn.allay.api.item.interfaces.element36;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement36Stack extends ItemStack {
    ItemType<ItemElement36Stack> ELEMENT_36_TYPE = ItemTypeBuilder
            .builder(ItemElement36Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_36)
            .build();
}
