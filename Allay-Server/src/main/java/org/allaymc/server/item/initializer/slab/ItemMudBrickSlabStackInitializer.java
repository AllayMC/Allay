package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemMudBrickSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickSlabStackInitializer {
  static void init() {
    ItemTypes.MUD_BRICK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMudBrickSlabStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_SLAB)
            .build();
  }
}
