package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneWallStack extends ItemStack {
  ItemType<ItemBlackstoneWallStack> BLACKSTONE_WALL_TYPE = ItemTypeBuilder
          .builder(ItemBlackstoneWallStack.class)
          .vanillaItem(VanillaItemId.BLACKSTONE_WALL)
          .build();
}
