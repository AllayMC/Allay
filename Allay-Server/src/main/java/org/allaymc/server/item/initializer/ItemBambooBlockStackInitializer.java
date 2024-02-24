package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBambooBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooBlockStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBambooBlockStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_BLOCK)
            .build();
  }
}
