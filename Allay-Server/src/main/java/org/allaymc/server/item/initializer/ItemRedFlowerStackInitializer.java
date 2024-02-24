package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedFlowerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedFlowerStackInitializer {
  static void init() {
    ItemTypes.RED_FLOWER_TYPE = ItemTypeBuilder
            .builder(ItemRedFlowerStack.class)
            .vanillaItem(VanillaItemId.RED_FLOWER)
            .build();
  }
}
