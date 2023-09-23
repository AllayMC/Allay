package cn.allay.api.item.interfaces.element85;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement85Stack extends ItemStack {
    ItemType<ItemElement85Stack> ELEMENT_85_TYPE = ItemTypeBuilder
            .builder(ItemElement85Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_85)
            .build();
}
