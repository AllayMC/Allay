package cn.allay.api.item.interfaces.exposedcutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCutCopperSlabStack extends ItemStack {
    ItemType<ItemExposedCutCopperSlabStack> EXPOSED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemExposedCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_CUT_COPPER_SLAB)
            .build();
}
