package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrackedDeepslateTilesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedDeepslateTilesStackInitializer {
  static void init() {
    ItemTypes.CRACKED_DEEPSLATE_TILES_TYPE = ItemTypeBuilder
            .builder(ItemCrackedDeepslateTilesStack.class)
            .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_TILES)
            .build();
  }
}
