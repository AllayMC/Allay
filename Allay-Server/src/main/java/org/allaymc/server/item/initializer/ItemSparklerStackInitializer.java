package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSparklerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSparklerStackInitializer {
  static void init() {
    ItemTypes.SPARKLER_TYPE = ItemTypeBuilder
            .builder(ItemSparklerStack.class)
            .vanillaItem(VanillaItemId.SPARKLER)
            .build();
  }
}
