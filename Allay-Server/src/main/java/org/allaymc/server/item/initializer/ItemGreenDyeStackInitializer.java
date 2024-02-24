package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGreenDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenDyeStackInitializer {
  static void init() {
    ItemTypes.GREEN_DYE_TYPE = ItemTypeBuilder
            .builder(ItemGreenDyeStack.class)
            .vanillaItem(VanillaItemId.GREEN_DYE)
            .build();
  }
}
