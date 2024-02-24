package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemBrownCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownCarpetStackInitializer {
  static void init() {
    ItemTypes.BROWN_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemBrownCarpetStack.class)
            .vanillaItem(VanillaItemId.BROWN_CARPET)
            .build();
  }
}
