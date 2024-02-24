package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemConduitStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConduitStackInitializer {
  static void init() {
    ItemTypes.CONDUIT_TYPE = ItemTypeBuilder
            .builder(ItemConduitStack.class)
            .vanillaItem(VanillaItemId.CONDUIT)
            .build();
  }
}
