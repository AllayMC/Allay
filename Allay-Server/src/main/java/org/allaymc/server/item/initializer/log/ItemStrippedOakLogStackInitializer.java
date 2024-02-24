package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedOakLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedOakLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedOakLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_OAK_LOG)
            .build();
  }
}
