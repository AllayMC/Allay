package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRottenFleshStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRottenFleshStackInitializer {
  static void init() {
    ItemTypes.ROTTEN_FLESH_TYPE = ItemTypeBuilder
            .builder(ItemRottenFleshStack.class)
            .vanillaItem(VanillaItemId.ROTTEN_FLESH)
            .build();
  }
}
