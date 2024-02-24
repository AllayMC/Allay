package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTuffStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffStackInitializer {
  static void init() {
    ItemTypes.TUFF_TYPE = ItemTypeBuilder
            .builder(ItemTuffStack.class)
            .vanillaItem(VanillaItemId.TUFF)
            .build();
  }
}
