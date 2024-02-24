package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedTuffStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffStackInitializer {
  static void init() {
    ItemTypes.POLISHED_TUFF_TYPE = ItemTypeBuilder
            .builder(ItemPolishedTuffStack.class)
            .vanillaItem(VanillaItemId.POLISHED_TUFF)
            .build();
  }
}
