package cn.allay.api.item.interfaces.element90;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement90Stack extends ItemStack {
    ItemType<ItemElement90Stack> ELEMENT_90_TYPE = ItemTypeBuilder
            .builder(ItemElement90Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_90)
            .build();
}
