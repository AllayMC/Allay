package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement47Stack extends ItemStack {
    ItemType<ItemElement47Stack> ELEMENT_47_TYPE = ItemTypeBuilder
            .builder(ItemElement47Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_47)
            .build();
}
