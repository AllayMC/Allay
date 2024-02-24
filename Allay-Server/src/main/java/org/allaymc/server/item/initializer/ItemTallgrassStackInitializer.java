package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTallgrassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTallgrassStackInitializer {
  static void init() {
    ItemTypes.TALLGRASS_TYPE = ItemTypeBuilder
            .builder(ItemTallgrassStack.class)
            .vanillaItem(VanillaItemId.TALLGRASS)
            .build();
  }
}
