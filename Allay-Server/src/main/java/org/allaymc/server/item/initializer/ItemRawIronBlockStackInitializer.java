package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRawIronBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawIronBlockStackInitializer {
  static void init() {
    ItemTypes.RAW_IRON_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRawIronBlockStack.class)
            .vanillaItem(VanillaItemId.RAW_IRON_BLOCK)
            .build();
  }
}
