package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCopperBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperBlockStackInitializer {
  static void init() {
    ItemTypes.COPPER_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemCopperBlockStack.class)
            .vanillaItem(VanillaItemId.COPPER_BLOCK)
            .build();
  }
}
