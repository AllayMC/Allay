package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRepeatingCommandBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRepeatingCommandBlockStackInitializer {
  static void init() {
    ItemTypes.REPEATING_COMMAND_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRepeatingCommandBlockStack.class)
            .vanillaItem(VanillaItemId.REPEATING_COMMAND_BLOCK)
            .build();
  }
}
