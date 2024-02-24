package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEmeraldBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmeraldBlockStackInitializer {
  static void init() {
    ItemTypes.EMERALD_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemEmeraldBlockStack.class)
            .vanillaItem(VanillaItemId.EMERALD_BLOCK)
            .build();
  }
}
