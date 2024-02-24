package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedJungleLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedJungleLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_JUNGLE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedJungleLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_JUNGLE_LOG)
            .build();
  }
}
