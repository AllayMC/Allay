package cn.allay.api.item.interfaces.doublecutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleCutCopperSlabStack extends ItemStack {
    ItemType<ItemDoubleCutCopperSlabStack> DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.DOUBLE_CUT_COPPER_SLAB)
            .build();
}
