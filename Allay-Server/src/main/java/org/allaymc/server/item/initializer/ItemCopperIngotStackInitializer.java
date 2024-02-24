package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCopperIngotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperIngotStackInitializer {
  static void init() {
    ItemTypes.COPPER_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemCopperIngotStack.class)
            .vanillaItem(VanillaItemId.COPPER_INGOT)
            .build();
  }
}
