package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCutCopperStack extends ItemStack {
    ItemType<ItemWaxedExposedCutCopperStack> WAXED_EXPOSED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCutCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_CUT_COPPER)
            .build();
}
