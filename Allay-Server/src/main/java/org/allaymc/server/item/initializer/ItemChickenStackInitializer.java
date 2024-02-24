package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChickenStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChickenStackInitializer {
  static void init() {
    ItemTypes.CHICKEN_TYPE = ItemTypeBuilder
            .builder(ItemChickenStack.class)
            .vanillaItem(VanillaItemId.CHICKEN)
            .build();
  }
}
