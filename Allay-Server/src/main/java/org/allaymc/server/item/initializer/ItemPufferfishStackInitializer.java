package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPufferfishStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishStackInitializer {
  static void init() {
    ItemTypes.PUFFERFISH_TYPE = ItemTypeBuilder
            .builder(ItemPufferfishStack.class)
            .vanillaItem(VanillaItemId.PUFFERFISH)
            .build();
  }
}
