package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLightBlueDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueDyeStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueDyeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_DYE)
            .build();
  }
}
