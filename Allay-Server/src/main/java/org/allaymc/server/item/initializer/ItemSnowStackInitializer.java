package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSnowStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowStackInitializer {
  static void init() {
    ItemTypes.SNOW_TYPE = ItemTypeBuilder
            .builder(ItemSnowStack.class)
            .vanillaItem(VanillaItemId.SNOW)
            .build();
  }
}
