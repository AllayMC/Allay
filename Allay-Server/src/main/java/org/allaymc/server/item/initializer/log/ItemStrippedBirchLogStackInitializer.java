package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedBirchLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedBirchLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_BIRCH_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedBirchLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_BIRCH_LOG)
            .build();
  }
}
