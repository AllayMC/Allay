package cn.allay.api.item.interfaces.oxidizedcutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCutCopperSlabStack extends ItemStack {
    ItemType<ItemOxidizedCutCopperSlabStack> OXIDIZED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_CUT_COPPER_SLAB)
            .build();
}
