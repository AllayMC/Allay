package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSweetBerriesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSweetBerriesStackInitializer {
  static void init() {
    ItemTypes.SWEET_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemSweetBerriesStack.class)
            .vanillaItem(VanillaItemId.SWEET_BERRIES)
            .build();
  }
}
