package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemDeepslateTileWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileWallStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_TILE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTileWallStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILE_WALL)
            .build();
  }
}
