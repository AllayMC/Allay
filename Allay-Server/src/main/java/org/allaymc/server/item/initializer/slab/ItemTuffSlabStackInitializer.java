package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemTuffSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffSlabStackInitializer {
  static void init() {
    ItemTypes.TUFF_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemTuffSlabStack.class)
            .vanillaItem(VanillaItemId.TUFF_SLAB)
            .build();
  }
}
