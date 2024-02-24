package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDeepslateBrickDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBrickDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_DOUBLE_SLAB)
            .build();
  }
}
