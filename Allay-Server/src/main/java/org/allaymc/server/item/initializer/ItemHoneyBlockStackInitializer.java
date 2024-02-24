package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHoneyBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoneyBlockStackInitializer {
  static void init() {
    ItemTypes.HONEY_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemHoneyBlockStack.class)
            .vanillaItem(VanillaItemId.HONEY_BLOCK)
            .build();
  }
}
