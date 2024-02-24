package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedDeepslateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateStackInitializer {
  static void init() {
    ItemTypes.POLISHED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE)
            .build();
  }
}
