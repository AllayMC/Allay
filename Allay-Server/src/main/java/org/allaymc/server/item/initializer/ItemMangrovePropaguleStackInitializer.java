package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMangrovePropaguleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangrovePropaguleStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_PROPAGULE_TYPE = ItemTypeBuilder
            .builder(ItemMangrovePropaguleStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_PROPAGULE)
            .build();
  }
}
