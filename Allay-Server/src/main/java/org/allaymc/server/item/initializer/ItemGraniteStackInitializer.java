package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGraniteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGraniteStackInitializer {
  static void init() {
    ItemTypes.GRANITE_TYPE = ItemTypeBuilder
            .builder(ItemGraniteStack.class)
            .vanillaItem(VanillaItemId.GRANITE)
            .build();
  }
}
