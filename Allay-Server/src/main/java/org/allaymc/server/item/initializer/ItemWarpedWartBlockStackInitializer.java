package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedWartBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedWartBlockStackInitializer {
  static void init() {
    ItemTypes.WARPED_WART_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemWarpedWartBlockStack.class)
            .vanillaItem(VanillaItemId.WARPED_WART_BLOCK)
            .build();
  }
}
