package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenAppleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenAppleStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_APPLE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenAppleStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_APPLE)
            .build();
  }
}
