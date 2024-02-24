package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCoralBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralBlockStackInitializer {
  static void init() {
    ItemTypes.CORAL_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemCoralBlockStack.class)
            .vanillaItem(VanillaItemId.CORAL_BLOCK)
            .build();
  }
}
