package cn.allay.api.item.interfaces.exposeddoublecutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedDoubleCutCopperSlabStack extends ItemStack {
    ItemType<ItemExposedDoubleCutCopperSlabStack> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemExposedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
            .build();
}
