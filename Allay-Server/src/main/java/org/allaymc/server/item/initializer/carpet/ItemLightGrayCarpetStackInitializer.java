package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemLightGrayCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCarpetStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayCarpetStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CARPET)
            .build();
  }
}
