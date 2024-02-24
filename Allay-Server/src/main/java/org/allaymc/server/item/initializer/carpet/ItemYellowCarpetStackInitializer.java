package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemYellowCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowCarpetStackInitializer {
  static void init() {
    ItemTypes.YELLOW_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemYellowCarpetStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CARPET)
            .build();
  }
}
