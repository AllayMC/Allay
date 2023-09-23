package cn.allay.api.item.interfaces.unknown;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnknownStack extends ItemStack {
    ItemType<ItemUnknownStack> UNKNOWN_TYPE = ItemTypeBuilder
            .builder(ItemUnknownStack.class)
            .vanillaItem(VanillaItemId.UNKNOWN)
            .build();
}
