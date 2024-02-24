package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemYellowFlowerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowFlowerStackInitializer {
  static void init() {
    ItemTypes.YELLOW_FLOWER_TYPE = ItemTypeBuilder
            .builder(ItemYellowFlowerStack.class)
            .vanillaItem(VanillaItemId.YELLOW_FLOWER)
            .build();
  }
}
