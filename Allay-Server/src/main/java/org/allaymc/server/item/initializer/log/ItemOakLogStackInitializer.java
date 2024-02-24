package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemOakLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakLogStackInitializer {
  static void init() {
    ItemTypes.OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemOakLogStack.class)
            .vanillaItem(VanillaItemId.OAK_LOG)
            .build();
  }
}
