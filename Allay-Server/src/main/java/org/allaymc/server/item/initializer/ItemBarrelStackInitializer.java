package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBarrelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBarrelStackInitializer {
  static void init() {
    ItemTypes.BARREL_TYPE = ItemTypeBuilder
            .builder(ItemBarrelStack.class)
            .vanillaItem(VanillaItemId.BARREL)
            .build();
  }
}
