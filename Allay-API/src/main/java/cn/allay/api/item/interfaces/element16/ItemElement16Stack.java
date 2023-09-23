package cn.allay.api.item.interfaces.element16;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement16Stack extends ItemStack {
    ItemType<ItemElement16Stack> ELEMENT_16_TYPE = ItemTypeBuilder
            .builder(ItemElement16Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_16)
            .build();
}
