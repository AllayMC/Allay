package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickWallStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneBrickWallStack> POLISHED_BLACKSTONE_BRICK_WALL_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneBrickWallStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_WALL)
          .build();
}
