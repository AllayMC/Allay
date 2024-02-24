package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemDarkOakLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakLogStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakLogStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_LOG)
            .build();
  }
}
