package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemBirchLogStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchLogStackInitializer {
  static void init() {
    ItemTypes.BIRCH_LOG_TYPE = ItemTypeBuilder
            .builder(ItemBirchLogStack.class)
            .vanillaItem(VanillaItemId.BIRCH_LOG)
            .build();
  }
}
