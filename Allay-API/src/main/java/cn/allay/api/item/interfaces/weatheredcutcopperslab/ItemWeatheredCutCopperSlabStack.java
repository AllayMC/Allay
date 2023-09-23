package cn.allay.api.item.interfaces.weatheredcutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperSlabStack extends ItemStack {
    ItemType<ItemWeatheredCutCopperSlabStack> WEATHERED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER_SLAB)
            .build();
}
