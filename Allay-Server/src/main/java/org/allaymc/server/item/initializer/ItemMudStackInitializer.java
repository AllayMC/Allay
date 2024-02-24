package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMudStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudStackInitializer {
  static void init() {
    ItemTypes.MUD_TYPE = ItemTypeBuilder
            .builder(ItemMudStack.class)
            .vanillaItem(VanillaItemId.MUD)
            .build();
  }
}
