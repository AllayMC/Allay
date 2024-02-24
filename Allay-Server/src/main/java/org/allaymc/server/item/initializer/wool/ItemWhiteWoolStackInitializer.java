package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemWhiteWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteWoolStackInitializer {
  static void init() {
    ItemTypes.WHITE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemWhiteWoolStack.class)
            .vanillaItem(VanillaItemId.WHITE_WOOL)
            .build();
  }
}
