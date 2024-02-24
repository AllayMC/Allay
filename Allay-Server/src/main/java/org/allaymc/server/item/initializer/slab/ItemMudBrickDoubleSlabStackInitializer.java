package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemMudBrickDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.MUD_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMudBrickDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_DOUBLE_SLAB)
            .build();
  }
}
