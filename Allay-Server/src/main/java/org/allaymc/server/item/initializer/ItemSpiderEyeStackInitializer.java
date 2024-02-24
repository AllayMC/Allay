package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSpiderEyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpiderEyeStackInitializer {
  static void init() {
    ItemTypes.SPIDER_EYE_TYPE = ItemTypeBuilder
            .builder(ItemSpiderEyeStack.class)
            .vanillaItem(VanillaItemId.SPIDER_EYE)
            .build();
  }
}
