package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement110Stack extends ItemStack {
    ItemType<ItemElement110Stack> ELEMENT_110_TYPE = ItemTypeBuilder
            .builder(ItemElement110Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_110)
            .build();
}
