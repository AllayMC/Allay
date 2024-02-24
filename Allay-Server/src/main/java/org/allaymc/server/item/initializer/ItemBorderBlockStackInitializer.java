package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBorderBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBorderBlockStackInitializer {
  static void init() {
    ItemTypes.BORDER_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBorderBlockStack.class)
            .vanillaItem(VanillaItemId.BORDER_BLOCK)
            .build();
  }
}
