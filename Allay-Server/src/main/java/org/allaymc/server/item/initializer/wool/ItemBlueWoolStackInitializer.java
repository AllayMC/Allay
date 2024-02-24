package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemBlueWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueWoolStackInitializer {
  static void init() {
    ItemTypes.BLUE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemBlueWoolStack.class)
            .vanillaItem(VanillaItemId.BLUE_WOOL)
            .build();
  }
}
