package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneDoubleSlabStack extends ItemStack {
  ItemType<ItemBlackstoneDoubleSlabStack> BLACKSTONE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemBlackstoneDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.BLACKSTONE_DOUBLE_SLAB)
          .build();
}
