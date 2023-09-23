package cn.allay.api.item.interfaces.element38;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement38Stack extends ItemStack {
    ItemType<ItemElement38Stack> ELEMENT_38_TYPE = ItemTypeBuilder
            .builder(ItemElement38Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_38)
            .build();
}
