package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemCherryLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryLogStackInitializer {
  static void init() {
    ItemTypes.CHERRY_LOG_TYPE = ItemTypeBuilder
            .builder(ItemCherryLogStack.class)
            .vanillaItem(VanillaItemId.CHERRY_LOG)
            .build();
  }
}
