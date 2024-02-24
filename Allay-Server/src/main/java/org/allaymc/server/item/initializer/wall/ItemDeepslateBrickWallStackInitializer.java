package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemDeepslateBrickWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickWallStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_BRICK_WALL_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBrickWallStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_WALL)
            .build();
  }
}
