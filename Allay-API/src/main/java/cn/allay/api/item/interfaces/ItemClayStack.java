package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClayStack extends ItemStack {
    ItemType<ItemClayStack> CLAY_TYPE = ItemTypeBuilder
            .builder(ItemClayStack.class)
            .vanillaItem(VanillaItemId.CLAY)
            .build();
}
