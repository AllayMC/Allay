package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMelonBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonBlockStackInitializer {
  static void init() {
    ItemTypes.MELON_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemMelonBlockStack.class)
            .vanillaItem(VanillaItemId.MELON_BLOCK)
            .build();
  }
}
