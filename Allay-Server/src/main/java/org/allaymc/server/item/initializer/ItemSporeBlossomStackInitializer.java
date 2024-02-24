package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSporeBlossomStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSporeBlossomStackInitializer {
  static void init() {
    ItemTypes.SPORE_BLOSSOM_TYPE = ItemTypeBuilder
            .builder(ItemSporeBlossomStack.class)
            .vanillaItem(VanillaItemId.SPORE_BLOSSOM)
            .build();
  }
}
