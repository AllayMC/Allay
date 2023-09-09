package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperStack extends ItemStack {
    ItemType<ItemWaxedCopperStack> WAXED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_COPPER)
            .build();
}
