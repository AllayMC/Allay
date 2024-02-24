package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCobbledDeepslateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateStackInitializer {
  static void init() {
    ItemTypes.COBBLED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE)
            .build();
  }
}
