package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement68Stack extends ItemStack {
    ItemType<ItemElement68Stack> ELEMENT_68_TYPE = ItemTypeBuilder
            .builder(ItemElement68Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_68)
            .build();
}
