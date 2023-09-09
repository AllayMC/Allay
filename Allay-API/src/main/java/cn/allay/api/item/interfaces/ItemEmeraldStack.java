package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmeraldStack extends ItemStack {
    ItemType<ItemEmeraldStack> EMERALD_TYPE = ItemTypeBuilder
            .builder(ItemEmeraldStack.class)
            .vanillaItem(VanillaItemId.EMERALD)
            .build();
}
