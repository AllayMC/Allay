package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE)
            .build();
  }
}
