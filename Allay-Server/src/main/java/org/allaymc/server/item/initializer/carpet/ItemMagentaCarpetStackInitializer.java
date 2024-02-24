package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemMagentaCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCarpetStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemMagentaCarpetStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CARPET)
            .build();
  }
}
