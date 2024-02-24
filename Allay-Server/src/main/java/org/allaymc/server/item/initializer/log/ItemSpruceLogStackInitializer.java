package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemSpruceLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceLogStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemSpruceLogStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_LOG)
            .build();
  }
}
