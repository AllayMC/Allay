package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCarrotsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotsStackInitializer {
  static void init() {
    ItemTypes.CARROTS_TYPE = ItemTypeBuilder
            .builder(ItemCarrotsStack.class)
            .vanillaItem(VanillaItemId.CARROTS)
            .build();
  }
}
