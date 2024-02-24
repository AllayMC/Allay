package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemMossCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossCarpetStackInitializer {
  static void init() {
    ItemTypes.MOSS_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemMossCarpetStack.class)
            .vanillaItem(VanillaItemId.MOSS_CARPET)
            .build();
  }
}
