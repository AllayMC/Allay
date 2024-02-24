package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGhastTearStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGhastTearStackInitializer {
  static void init() {
    ItemTypes.GHAST_TEAR_TYPE = ItemTypeBuilder
            .builder(ItemGhastTearStack.class)
            .vanillaItem(VanillaItemId.GHAST_TEAR)
            .build();
  }
}
