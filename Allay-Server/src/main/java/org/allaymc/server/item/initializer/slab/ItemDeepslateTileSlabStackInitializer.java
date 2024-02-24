package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDeepslateTileSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileSlabStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_TILE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTileSlabStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILE_SLAB)
            .build();
  }
}
