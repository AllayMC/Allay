package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFlowerPotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowerPotStackInitializer {
  static void init() {
    ItemTypes.FLOWER_POT_TYPE = ItemTypeBuilder
            .builder(ItemFlowerPotStack.class)
            .vanillaItem(VanillaItemId.FLOWER_POT)
            .build();
  }
}
