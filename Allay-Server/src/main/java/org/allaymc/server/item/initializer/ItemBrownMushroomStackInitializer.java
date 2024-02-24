package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrownMushroomStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownMushroomStackInitializer {
  static void init() {
    ItemTypes.BROWN_MUSHROOM_TYPE = ItemTypeBuilder
            .builder(ItemBrownMushroomStack.class)
            .vanillaItem(VanillaItemId.BROWN_MUSHROOM)
            .build();
  }
}
