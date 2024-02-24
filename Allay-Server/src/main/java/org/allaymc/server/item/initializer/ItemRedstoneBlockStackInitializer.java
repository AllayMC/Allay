package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedstoneBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneBlockStackInitializer {
  static void init() {
    ItemTypes.REDSTONE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRedstoneBlockStack.class)
            .vanillaItem(VanillaItemId.REDSTONE_BLOCK)
            .build();
  }
}
