package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemTuffBrickDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBrickDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.TUFF_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemTuffBrickDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.TUFF_BRICK_DOUBLE_SLAB)
            .build();
  }
}
