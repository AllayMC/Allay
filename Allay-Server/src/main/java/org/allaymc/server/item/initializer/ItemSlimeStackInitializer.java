package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSlimeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSlimeStackInitializer {
  static void init() {
    ItemTypes.SLIME_TYPE = ItemTypeBuilder
            .builder(ItemSlimeStack.class)
            .vanillaItem(VanillaItemId.SLIME)
            .build();
  }
}
