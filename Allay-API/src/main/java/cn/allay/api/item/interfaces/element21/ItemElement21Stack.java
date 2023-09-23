package cn.allay.api.item.interfaces.element21;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement21Stack extends ItemStack {
    ItemType<ItemElement21Stack> ELEMENT_21_TYPE = ItemTypeBuilder
            .builder(ItemElement21Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_21)
            .build();
}
