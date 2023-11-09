package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickWallStack extends ItemStack {
  ItemType<ItemMudBrickWallStack> MUD_BRICK_WALL_TYPE = ItemTypeBuilder
          .builder(ItemMudBrickWallStack.class)
          .vanillaItem(VanillaItemId.MUD_BRICK_WALL)
          .build();
}
