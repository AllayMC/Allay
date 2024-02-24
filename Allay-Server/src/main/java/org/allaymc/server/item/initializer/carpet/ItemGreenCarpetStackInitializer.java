package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemGreenCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenCarpetStackInitializer {
  static void init() {
    ItemTypes.GREEN_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemGreenCarpetStack.class)
            .vanillaItem(VanillaItemId.GREEN_CARPET)
            .build();
  }
}
