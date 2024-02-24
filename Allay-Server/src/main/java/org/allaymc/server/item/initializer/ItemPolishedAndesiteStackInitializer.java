package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedAndesiteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedAndesiteStackInitializer {
  static void init() {
    ItemTypes.POLISHED_ANDESITE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedAndesiteStack.class)
            .vanillaItem(VanillaItemId.POLISHED_ANDESITE)
            .build();
  }
}
