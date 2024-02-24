package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLightGrayDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayDyeStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_DYE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayDyeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_DYE)
            .build();
  }
}
