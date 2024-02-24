package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCompassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCompassStackInitializer {
  static void init() {
    ItemTypes.COMPASS_TYPE = ItemTypeBuilder
            .builder(ItemCompassStack.class)
            .vanillaItem(VanillaItemId.COMPASS)
            .build();
  }
}
