package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSmokerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmokerStackInitializer {
  static void init() {
    ItemTypes.SMOKER_TYPE = ItemTypeBuilder
            .builder(ItemSmokerStack.class)
            .vanillaItem(VanillaItemId.SMOKER)
            .build();
  }
}
