package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedMushroomStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedMushroomStackInitializer {
  static void init() {
    ItemTypes.RED_MUSHROOM_TYPE = ItemTypeBuilder
            .builder(ItemRedMushroomStack.class)
            .vanillaItem(VanillaItemId.RED_MUSHROOM)
            .build();
  }
}
