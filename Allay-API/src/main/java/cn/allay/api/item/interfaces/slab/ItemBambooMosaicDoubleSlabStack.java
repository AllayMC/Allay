package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicDoubleSlabStack extends ItemStack {
  ItemType<ItemBambooMosaicDoubleSlabStack> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemBambooMosaicDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_MOSAIC_DOUBLE_SLAB)
          .build();
}
