package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicStack extends ItemStack {
  ItemType<ItemBambooMosaicStack> BAMBOO_MOSAIC_TYPE = ItemTypeBuilder
          .builder(ItemBambooMosaicStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_MOSAIC)
          .build();
}
