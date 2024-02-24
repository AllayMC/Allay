package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoolStackInitializer {
  static void init() {
    ItemTypes.WOOL_TYPE = ItemTypeBuilder
            .builder(ItemWoolStack.class)
            .vanillaItem(VanillaItemId.WOOL)
            .build();
  }
}
