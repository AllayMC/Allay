package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHeartOfTheSeaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeartOfTheSeaStackInitializer {
  static void init() {
    ItemTypes.HEART_OF_THE_SEA_TYPE = ItemTypeBuilder
            .builder(ItemHeartOfTheSeaStack.class)
            .vanillaItem(VanillaItemId.HEART_OF_THE_SEA)
            .build();
  }
}
