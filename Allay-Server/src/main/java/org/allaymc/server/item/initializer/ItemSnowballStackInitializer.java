package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSnowballStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowballStackInitializer {
  static void init() {
    ItemTypes.SNOWBALL_TYPE = ItemTypeBuilder
            .builder(ItemSnowballStack.class)
            .vanillaItem(VanillaItemId.SNOWBALL)
            .build();
  }
}
