package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStonecutterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonecutterStackInitializer {
  static void init() {
    ItemTypes.STONECUTTER_TYPE = ItemTypeBuilder
            .builder(ItemStonecutterStack.class)
            .vanillaItem(VanillaItemId.STONECUTTER)
            .build();
  }
}
