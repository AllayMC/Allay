package cn.allay.api.item.interfaces.element18;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement18Stack extends ItemStack {
    ItemType<ItemElement18Stack> ELEMENT_18_TYPE = ItemTypeBuilder
            .builder(ItemElement18Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_18)
            .build();
}
