package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiscFragment5Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiscFragment5StackInitializer {
  static void init() {
    ItemTypes.DISC_FRAGMENT_5_TYPE = ItemTypeBuilder
            .builder(ItemDiscFragment5Stack.class)
            .vanillaItem(VanillaItemId.DISC_FRAGMENT_5)
            .build();
  }
}
