package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFarmlandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFarmlandStackInitializer {
  static void init() {
    ItemTypes.FARMLAND_TYPE = ItemTypeBuilder
            .builder(ItemFarmlandStack.class)
            .vanillaItem(VanillaItemId.FARMLAND)
            .build();
  }
}
