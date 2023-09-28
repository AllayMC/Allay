package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlabStack extends ItemStack {
  ItemType<ItemDoubleStoneBlockSlabStack> DOUBLE_STONE_BLOCK_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemDoubleStoneBlockSlabStack.class)
          .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB)
          .build();
}
