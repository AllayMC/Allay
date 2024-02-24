package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlisteringMelonSliceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlisteringMelonSliceStackInitializer {
  static void init() {
    ItemTypes.GLISTERING_MELON_SLICE_TYPE = ItemTypeBuilder
            .builder(ItemGlisteringMelonSliceStack.class)
            .vanillaItem(VanillaItemId.GLISTERING_MELON_SLICE)
            .build();
  }
}
