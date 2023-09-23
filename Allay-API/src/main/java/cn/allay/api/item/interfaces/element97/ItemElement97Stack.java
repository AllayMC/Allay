package cn.allay.api.item.interfaces.element97;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement97Stack extends ItemStack {
    ItemType<ItemElement97Stack> ELEMENT_97_TYPE = ItemTypeBuilder
            .builder(ItemElement97Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_97)
            .build();
}
