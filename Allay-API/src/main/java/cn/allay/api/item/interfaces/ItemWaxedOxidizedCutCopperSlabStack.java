package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCutCopperSlabStack extends ItemStack {
    ItemType<ItemWaxedOxidizedCutCopperSlabStack> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
            .build();
}
