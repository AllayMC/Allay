package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteIngotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteIngotStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteIngotStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_INGOT)
            .build();
  }
}
