package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredDoubleCutCopperSlabStack extends ItemStack {
  ItemType<ItemWeatheredDoubleCutCopperSlabStack> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemWeatheredDoubleCutCopperSlabStack.class)
          .vanillaItem(VanillaItemId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
          .build();
}
