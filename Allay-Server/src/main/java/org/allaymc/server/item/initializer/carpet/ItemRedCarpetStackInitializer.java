package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemRedCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCarpetStackInitializer {
  static void init() {
    ItemTypes.RED_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemRedCarpetStack.class)
            .vanillaItem(VanillaItemId.RED_CARPET)
            .build();
  }
}
