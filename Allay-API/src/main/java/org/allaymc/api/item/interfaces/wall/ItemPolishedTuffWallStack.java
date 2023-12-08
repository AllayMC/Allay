package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffWallStack extends ItemStack {
  ItemType<ItemPolishedTuffWallStack> POLISHED_TUFF_WALL_TYPE = ItemTypeBuilder
          .builder(ItemPolishedTuffWallStack.class)
          .vanillaItem(VanillaItemId.POLISHED_TUFF_WALL)
          .build();
}
