package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement48Stack extends ItemStack {
    ItemType<ItemElement48Stack> ELEMENT_48_TYPE = ItemTypeBuilder
            .builder(ItemElement48Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_48)
            .build();
}
