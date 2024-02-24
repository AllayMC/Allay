package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGrayDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayDyeStackInitializer {
  static void init() {
    ItemTypes.GRAY_DYE_TYPE = ItemTypeBuilder
            .builder(ItemGrayDyeStack.class)
            .vanillaItem(VanillaItemId.GRAY_DYE)
            .build();
  }
}
