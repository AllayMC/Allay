package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenSwordStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenSwordStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemGoldenSwordStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_SWORD)
            .build();
  }
}
