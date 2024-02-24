package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoatHornStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoatHornStackInitializer {
  static void init() {
    ItemTypes.GOAT_HORN_TYPE = ItemTypeBuilder
            .builder(ItemGoatHornStack.class)
            .vanillaItem(VanillaItemId.GOAT_HORN)
            .build();
  }
}
