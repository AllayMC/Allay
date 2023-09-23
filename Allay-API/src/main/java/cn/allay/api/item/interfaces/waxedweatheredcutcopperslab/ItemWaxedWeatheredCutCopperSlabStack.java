package cn.allay.api.item.interfaces.waxedweatheredcutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCutCopperSlabStack extends ItemStack {
    ItemType<ItemWaxedWeatheredCutCopperSlabStack> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_CUT_COPPER_SLAB)
            .build();
}
