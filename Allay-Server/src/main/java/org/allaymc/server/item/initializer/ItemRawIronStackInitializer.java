package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRawIronStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawIronStackInitializer {
  static void init() {
    ItemTypes.RAW_IRON_TYPE = ItemTypeBuilder
            .builder(ItemRawIronStack.class)
            .vanillaItem(VanillaItemId.RAW_IRON)
            .build();
  }
}
