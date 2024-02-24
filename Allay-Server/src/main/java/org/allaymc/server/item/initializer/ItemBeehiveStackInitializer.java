package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBeehiveStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeehiveStackInitializer {
  static void init() {
    ItemTypes.BEEHIVE_TYPE = ItemTypeBuilder
            .builder(ItemBeehiveStack.class)
            .vanillaItem(VanillaItemId.BEEHIVE)
            .build();
  }
}
