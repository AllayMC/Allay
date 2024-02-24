package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemStrippedAcaciaLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedAcaciaLogStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_ACACIA_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedAcaciaLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_ACACIA_LOG)
            .build();
  }
}
