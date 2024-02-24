package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemLimeCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeCarpetStackInitializer {
  static void init() {
    ItemTypes.LIME_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemLimeCarpetStack.class)
            .vanillaItem(VanillaItemId.LIME_CARPET)
            .build();
  }
}
