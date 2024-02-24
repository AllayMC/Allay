package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemGrayCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCarpetStackInitializer {
  static void init() {
    ItemTypes.GRAY_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemGrayCarpetStack.class)
            .vanillaItem(VanillaItemId.GRAY_CARPET)
            .build();
  }
}
