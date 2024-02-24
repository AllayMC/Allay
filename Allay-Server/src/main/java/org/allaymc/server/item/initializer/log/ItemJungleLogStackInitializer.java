package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemJungleLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleLogStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemJungleLogStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_LOG)
            .build();
  }
}
