package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedMangroveLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedMangroveLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_MANGROVE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedMangroveLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_MANGROVE_LOG)
            .build();
  }
}
