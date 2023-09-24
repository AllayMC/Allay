package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement7Stack extends ItemStack {
    ItemType<ItemElement7Stack> ELEMENT_7_TYPE = ItemTypeBuilder
            .builder(ItemElement7Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_7)
            .build();
}
