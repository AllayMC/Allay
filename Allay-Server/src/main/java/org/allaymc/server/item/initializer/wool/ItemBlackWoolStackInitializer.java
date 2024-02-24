package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemBlackWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackWoolStackInitializer {
  static void init() {
    ItemTypes.BLACK_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemBlackWoolStack.class)
            .vanillaItem(VanillaItemId.BLACK_WOOL)
            .build();
  }
}
