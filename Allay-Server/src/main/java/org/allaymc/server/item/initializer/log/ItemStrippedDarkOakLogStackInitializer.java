package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedDarkOakLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedDarkOakLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_DARK_OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedDarkOakLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_DARK_OAK_LOG)
            .build();
  }
}
