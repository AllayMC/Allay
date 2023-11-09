package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileWallStack extends ItemStack {
  ItemType<ItemDeepslateTileWallStack> DEEPSLATE_TILE_WALL_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateTileWallStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_TILE_WALL)
          .build();
}
