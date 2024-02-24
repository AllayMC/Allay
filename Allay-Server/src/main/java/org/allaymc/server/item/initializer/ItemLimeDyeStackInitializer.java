package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLimeDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeDyeStackInitializer {
  static void init() {
    ItemTypes.LIME_DYE_TYPE = ItemTypeBuilder
            .builder(ItemLimeDyeStack.class)
            .vanillaItem(VanillaItemId.LIME_DYE)
            .build();
  }
}
