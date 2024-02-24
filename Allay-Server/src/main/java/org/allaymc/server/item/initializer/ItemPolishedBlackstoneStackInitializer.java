package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedBlackstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE)
            .build();
  }
}
