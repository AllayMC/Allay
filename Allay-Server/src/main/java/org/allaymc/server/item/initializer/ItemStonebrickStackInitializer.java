package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStonebrickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonebrickStackInitializer {
  static void init() {
    ItemTypes.STONEBRICK_TYPE = ItemTypeBuilder
            .builder(ItemStonebrickStack.class)
            .vanillaItem(VanillaItemId.STONEBRICK)
            .build();
  }
}
