package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemYellowDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowDyeStackInitializer {
  static void init() {
    ItemTypes.YELLOW_DYE_TYPE = ItemTypeBuilder
            .builder(ItemYellowDyeStack.class)
            .vanillaItem(VanillaItemId.YELLOW_DYE)
            .build();
  }
}
