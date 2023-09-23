package cn.allay.api.item.interfaces.element45;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement45Stack extends ItemStack {
    ItemType<ItemElement45Stack> ELEMENT_45_TYPE = ItemTypeBuilder
            .builder(ItemElement45Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_45)
            .build();
}
