package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCommandBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCommandBlockStackInitializer {
  static void init() {
    ItemTypes.COMMAND_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemCommandBlockStack.class)
            .vanillaItem(VanillaItemId.COMMAND_BLOCK)
            .build();
  }
}
