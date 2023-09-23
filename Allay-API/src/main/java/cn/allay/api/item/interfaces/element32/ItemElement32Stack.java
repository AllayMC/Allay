package cn.allay.api.item.interfaces.element32;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement32Stack extends ItemStack {
    ItemType<ItemElement32Stack> ELEMENT_32_TYPE = ItemTypeBuilder
            .builder(ItemElement32Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_32)
            .build();
}
