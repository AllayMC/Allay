package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedDoubleCutCopperSlabStack extends ItemStack {
    ItemType<ItemOxidizedDoubleCutCopperSlabStack> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
            .build();
}
