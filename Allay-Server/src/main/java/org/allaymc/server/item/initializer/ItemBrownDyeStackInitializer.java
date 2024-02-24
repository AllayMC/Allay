package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrownDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownDyeStackInitializer {
  static void init() {
    ItemTypes.BROWN_DYE_TYPE = ItemTypeBuilder
            .builder(ItemBrownDyeStack.class)
            .vanillaItem(VanillaItemId.BROWN_DYE)
            .build();
  }
}
