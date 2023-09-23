package cn.allay.api.item.interfaces.element113;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement113Stack extends ItemStack {
    ItemType<ItemElement113Stack> ELEMENT_113_TYPE = ItemTypeBuilder
            .builder(ItemElement113Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_113)
            .build();
}
