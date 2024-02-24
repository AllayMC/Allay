package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAzaleaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaStackInitializer {
  static void init() {
    ItemTypes.AZALEA_TYPE = ItemTypeBuilder
            .builder(ItemAzaleaStack.class)
            .vanillaItem(VanillaItemId.AZALEA)
            .build();
  }
}
