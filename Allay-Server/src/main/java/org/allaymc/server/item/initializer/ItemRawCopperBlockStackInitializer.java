package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRawCopperBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawCopperBlockStackInitializer {
  static void init() {
    ItemTypes.RAW_COPPER_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRawCopperBlockStack.class)
            .vanillaItem(VanillaItemId.RAW_COPPER_BLOCK)
            .build();
  }
}
