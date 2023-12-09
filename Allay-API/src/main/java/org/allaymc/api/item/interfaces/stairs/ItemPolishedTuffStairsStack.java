package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffStairsStack extends ItemStack {
  ItemType<ItemPolishedTuffStairsStack> POLISHED_TUFF_STAIRS_TYPE = ItemTypeBuilder
          .builder(ItemPolishedTuffStairsStack.class)
          .vanillaItem(VanillaItemId.POLISHED_TUFF_STAIRS)
          .build();
}
