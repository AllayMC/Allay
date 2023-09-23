package cn.allay.api.item.interfaces.element50;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement50Stack extends ItemStack {
    ItemType<ItemElement50Stack> ELEMENT_50_TYPE = ItemTypeBuilder
            .builder(ItemElement50Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_50)
            .build();
}
