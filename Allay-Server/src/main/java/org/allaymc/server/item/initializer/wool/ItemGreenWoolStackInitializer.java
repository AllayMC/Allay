package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemGreenWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenWoolStackInitializer {
  static void init() {
    ItemTypes.GREEN_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemGreenWoolStack.class)
            .vanillaItem(VanillaItemId.GREEN_WOOL)
            .build();
  }
}
