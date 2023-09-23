package cn.allay.api.item.interfaces.element117;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement117Stack extends ItemStack {
    ItemType<ItemElement117Stack> ELEMENT_117_TYPE = ItemTypeBuilder
            .builder(ItemElement117Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_117)
            .build();
}
