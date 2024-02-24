package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTrappedChestStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrappedChestStackInitializer {
  static void init() {
    ItemTypes.TRAPPED_CHEST_TYPE = ItemTypeBuilder
            .builder(ItemTrappedChestStack.class)
            .vanillaItem(VanillaItemId.TRAPPED_CHEST)
            .build();
  }
}
