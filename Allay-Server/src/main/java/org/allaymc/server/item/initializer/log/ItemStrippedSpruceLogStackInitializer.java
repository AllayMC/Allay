package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedSpruceLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedSpruceLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_SPRUCE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedSpruceLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_SPRUCE_LOG)
            .build();
  }
}
