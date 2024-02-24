package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenHoeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHoeStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_HOE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenHoeStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_HOE)
            .build();
  }
}
