package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneWallStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneWallStack> POLISHED_BLACKSTONE_WALL_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneWallStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_WALL)
          .build();
}
