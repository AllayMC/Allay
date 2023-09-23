package cn.allay.api.item.interfaces.element80;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement80Stack extends ItemStack {
    ItemType<ItemElement80Stack> ELEMENT_80_TYPE = ItemTypeBuilder
            .builder(ItemElement80Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_80)
            .build();
}
