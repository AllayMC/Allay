package cn.allay.api.item.interfaces.element62;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement62Stack extends ItemStack {
    ItemType<ItemElement62Stack> ELEMENT_62_TYPE = ItemTypeBuilder
            .builder(ItemElement62Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_62)
            .build();
}
