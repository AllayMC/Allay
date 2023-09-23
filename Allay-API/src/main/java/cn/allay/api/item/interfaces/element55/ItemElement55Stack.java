package cn.allay.api.item.interfaces.element55;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement55Stack extends ItemStack {
    ItemType<ItemElement55Stack> ELEMENT_55_TYPE = ItemTypeBuilder
            .builder(ItemElement55Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_55)
            .build();
}
