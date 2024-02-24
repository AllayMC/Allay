package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPurpurBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpurBlockStackInitializer {
  static void init() {
    ItemTypes.PURPUR_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemPurpurBlockStack.class)
            .vanillaItem(VanillaItemId.PURPUR_BLOCK)
            .build();
  }
}
