package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMelonSliceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonSliceStackInitializer {
  static void init() {
    ItemTypes.MELON_SLICE_TYPE = ItemTypeBuilder
            .builder(ItemMelonSliceStack.class)
            .vanillaItem(VanillaItemId.MELON_SLICE)
            .build();
  }
}
