package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPackedMudStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPackedMudStackInitializer {
  static void init() {
    ItemTypes.PACKED_MUD_TYPE = ItemTypeBuilder
            .builder(ItemPackedMudStack.class)
            .vanillaItem(VanillaItemId.PACKED_MUD)
            .build();
  }
}
