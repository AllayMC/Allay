package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemMangroveLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveLogStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemMangroveLogStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_LOG)
            .build();
  }
}
