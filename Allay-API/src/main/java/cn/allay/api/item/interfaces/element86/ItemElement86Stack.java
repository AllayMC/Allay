package cn.allay.api.item.interfaces.element86;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement86Stack extends ItemStack {
    ItemType<ItemElement86Stack> ELEMENT_86_TYPE = ItemTypeBuilder
            .builder(ItemElement86Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_86)
            .build();
}
