package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrafterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrafterStackInitializer {
  static void init() {
    ItemTypes.CRAFTER_TYPE = ItemTypeBuilder
            .builder(ItemCrafterStack.class)
            .vanillaItem(VanillaItemId.CRAFTER)
            .build();
  }
}
