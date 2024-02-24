package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStrippedBambooBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedBambooBlockStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_BAMBOO_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemStrippedBambooBlockStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_BAMBOO_BLOCK)
            .build();
  }
}
