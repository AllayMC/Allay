package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronBlockStackInitializer {
  static void init() {
    ItemTypes.IRON_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemIronBlockStack.class)
            .vanillaItem(VanillaItemId.IRON_BLOCK)
            .build();
  }
}
