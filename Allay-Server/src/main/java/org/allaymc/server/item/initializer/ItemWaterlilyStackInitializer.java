package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaterlilyStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterlilyStackInitializer {
  static void init() {
    ItemTypes.WATERLILY_TYPE = ItemTypeBuilder
            .builder(ItemWaterlilyStack.class)
            .vanillaItem(VanillaItemId.WATERLILY)
            .build();
  }
}
