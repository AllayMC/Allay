package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteHoeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteHoeStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_HOE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteHoeStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_HOE)
            .build();
  }
}
