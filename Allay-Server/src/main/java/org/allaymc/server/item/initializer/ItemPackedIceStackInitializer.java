package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPackedIceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPackedIceStackInitializer {
  static void init() {
    ItemTypes.PACKED_ICE_TYPE = ItemTypeBuilder
            .builder(ItemPackedIceStack.class)
            .vanillaItem(VanillaItemId.PACKED_ICE)
            .build();
  }
}
