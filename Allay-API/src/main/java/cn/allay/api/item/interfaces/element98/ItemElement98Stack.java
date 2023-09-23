package cn.allay.api.item.interfaces.element98;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement98Stack extends ItemStack {
    ItemType<ItemElement98Stack> ELEMENT_98_TYPE = ItemTypeBuilder
            .builder(ItemElement98Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_98)
            .build();
}
