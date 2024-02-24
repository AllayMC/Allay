package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemYellowWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowWoolStackInitializer {
  static void init() {
    ItemTypes.YELLOW_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemYellowWoolStack.class)
            .vanillaItem(VanillaItemId.YELLOW_WOOL)
            .build();
  }
}
