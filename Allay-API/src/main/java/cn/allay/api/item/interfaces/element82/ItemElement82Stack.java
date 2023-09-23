package cn.allay.api.item.interfaces.element82;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement82Stack extends ItemStack {
    ItemType<ItemElement82Stack> ELEMENT_82_TYPE = ItemTypeBuilder
            .builder(ItemElement82Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_82)
            .build();
}
