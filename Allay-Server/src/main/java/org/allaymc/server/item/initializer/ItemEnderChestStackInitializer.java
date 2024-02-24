package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEnderChestStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderChestStackInitializer {
  static void init() {
    ItemTypes.ENDER_CHEST_TYPE = ItemTypeBuilder
            .builder(ItemEnderChestStack.class)
            .vanillaItem(VanillaItemId.ENDER_CHEST)
            .build();
  }
}
