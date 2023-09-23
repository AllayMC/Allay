package cn.allay.api.item.interfaces.element58;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement58Stack extends ItemStack {
    ItemType<ItemElement58Stack> ELEMENT_58_TYPE = ItemTypeBuilder
            .builder(ItemElement58Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_58)
            .build();
}
