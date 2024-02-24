package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemLightBlueCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueCarpetStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueCarpetStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CARPET)
            .build();
  }
}
