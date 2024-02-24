package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBambooStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_TYPE = ItemTypeBuilder
            .builder(ItemBambooStack.class)
            .vanillaItem(VanillaItemId.BAMBOO)
            .build();
  }
}
