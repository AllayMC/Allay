package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCocoaStack extends ItemStack {
    ItemType<ItemCocoaStack> COCOA_TYPE = ItemTypeBuilder
            .builder(ItemCocoaStack.class)
            .vanillaItem(VanillaItemId.COCOA)
            .build();
}
