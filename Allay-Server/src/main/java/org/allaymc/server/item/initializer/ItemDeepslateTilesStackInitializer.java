package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateTilesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTilesStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_TILES_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTilesStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILES)
            .build();
  }
}
