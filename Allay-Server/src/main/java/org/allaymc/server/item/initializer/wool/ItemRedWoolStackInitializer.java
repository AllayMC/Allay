package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemRedWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedWoolStackInitializer {
  static void init() {
    ItemTypes.RED_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemRedWoolStack.class)
            .vanillaItem(VanillaItemId.RED_WOOL)
            .build();
  }
}
