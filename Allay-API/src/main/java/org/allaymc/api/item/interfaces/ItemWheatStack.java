package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWheatStack extends ItemStack {
  ItemType<ItemWheatStack> WHEAT_TYPE = ItemTypeBuilder
          .builder(ItemWheatStack.class)
          .vanillaItem(VanillaItemId.WHEAT)
          .build();
}
