package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemAcaciaLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaLogStackInitializer {
  static void init() {
    ItemTypes.ACACIA_LOG_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaLogStack.class)
            .vanillaItem(VanillaItemId.ACACIA_LOG)
            .build();
  }
}
