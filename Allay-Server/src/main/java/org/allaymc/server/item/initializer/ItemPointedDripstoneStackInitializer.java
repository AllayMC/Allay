package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPointedDripstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPointedDripstoneStackInitializer {
  static void init() {
    ItemTypes.POINTED_DRIPSTONE_TYPE = ItemTypeBuilder
            .builder(ItemPointedDripstoneStack.class)
            .vanillaItem(VanillaItemId.POINTED_DRIPSTONE)
            .build();
  }
}
