package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement88Stack extends ItemStack {
    ItemType<ItemElement88Stack> ELEMENT_88_TYPE = ItemTypeBuilder
            .builder(ItemElement88Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_88)
            .build();
}
