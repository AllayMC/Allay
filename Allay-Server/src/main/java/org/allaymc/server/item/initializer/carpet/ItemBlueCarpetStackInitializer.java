package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemBlueCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueCarpetStackInitializer {
  static void init() {
    ItemTypes.BLUE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemBlueCarpetStack.class)
            .vanillaItem(VanillaItemId.BLUE_CARPET)
            .build();
  }
}
