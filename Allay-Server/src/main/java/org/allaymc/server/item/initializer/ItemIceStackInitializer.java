package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIceStackInitializer {
  static void init() {
    ItemTypes.ICE_TYPE = ItemTypeBuilder
            .builder(ItemIceStack.class)
            .vanillaItem(VanillaItemId.ICE)
            .build();
  }
}
