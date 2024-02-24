package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlazePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazePowderStackInitializer {
  static void init() {
    ItemTypes.BLAZE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemBlazePowderStack.class)
            .vanillaItem(VanillaItemId.BLAZE_POWDER)
            .build();
  }
}
