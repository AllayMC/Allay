package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemInfoUpdate2Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfoUpdate2StackInitializer {
  static void init() {
    ItemTypes.INFO_UPDATE2_TYPE = ItemTypeBuilder
            .builder(ItemInfoUpdate2Stack.class)
            .vanillaItem(VanillaItemId.INFO_UPDATE2)
            .build();
  }
}
