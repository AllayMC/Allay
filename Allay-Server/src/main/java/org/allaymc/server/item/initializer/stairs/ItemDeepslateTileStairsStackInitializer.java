package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemDeepslateTileStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileStairsStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_TILE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTileStairsStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILE_STAIRS)
            .build();
  }
}
