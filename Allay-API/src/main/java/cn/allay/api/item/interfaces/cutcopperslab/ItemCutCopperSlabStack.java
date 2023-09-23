package cn.allay.api.item.interfaces.cutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCutCopperSlabStack extends ItemStack {
    ItemType<ItemCutCopperSlabStack> CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.CUT_COPPER_SLAB)
            .build();
}
