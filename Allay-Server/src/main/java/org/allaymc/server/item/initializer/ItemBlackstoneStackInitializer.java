package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlackstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneStackInitializer {
  static void init() {
    ItemTypes.BLACKSTONE_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE)
            .build();
  }
}
