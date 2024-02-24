package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemPinkCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCarpetStackInitializer {
  static void init() {
    ItemTypes.PINK_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemPinkCarpetStack.class)
            .vanillaItem(VanillaItemId.PINK_CARPET)
            .build();
  }
}
