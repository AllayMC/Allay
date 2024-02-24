package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedCherryLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCherryLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_CHERRY_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedCherryLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_CHERRY_LOG)
            .build();
  }
}
