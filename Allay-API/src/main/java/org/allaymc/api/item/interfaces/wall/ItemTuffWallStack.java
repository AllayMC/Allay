package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffWallStack extends ItemStack {
  ItemType<ItemTuffWallStack> TUFF_WALL_TYPE = ItemTypeBuilder
          .builder(ItemTuffWallStack.class)
          .vanillaItem(VanillaItemId.TUFF_WALL)
          .build();
}
