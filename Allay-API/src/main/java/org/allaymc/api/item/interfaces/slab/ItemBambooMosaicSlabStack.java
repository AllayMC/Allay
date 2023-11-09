package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicSlabStack extends ItemStack {
  ItemType<ItemBambooMosaicSlabStack> BAMBOO_MOSAIC_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemBambooMosaicSlabStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_MOSAIC_SLAB)
          .build();
}
