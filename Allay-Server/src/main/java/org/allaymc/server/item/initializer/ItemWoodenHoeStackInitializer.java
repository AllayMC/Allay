package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWoodenHoeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenHoeStackInitializer {
  static void init() {
    ItemTypes.WOODEN_HOE_TYPE = ItemTypeBuilder
            .builder(ItemWoodenHoeStack.class)
            .vanillaItem(VanillaItemId.WOODEN_HOE)
            .build();
  }
}
