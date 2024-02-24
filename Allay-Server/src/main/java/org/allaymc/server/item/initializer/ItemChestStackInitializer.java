package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChestStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChestStackInitializer {
  static void init() {
    ItemTypes.CHEST_TYPE = ItemTypeBuilder
            .builder(ItemChestStack.class)
            .vanillaItem(VanillaItemId.CHEST)
            .build();
  }
}
