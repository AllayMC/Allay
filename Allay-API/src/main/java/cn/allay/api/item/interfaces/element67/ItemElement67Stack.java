package cn.allay.api.item.interfaces.element67;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement67Stack extends ItemStack {
    ItemType<ItemElement67Stack> ELEMENT_67_TYPE = ItemTypeBuilder
            .builder(ItemElement67Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_67)
            .build();
}
