package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSugarStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSugarStackInitializer {
  static void init() {
    ItemTypes.SUGAR_TYPE = ItemTypeBuilder
            .builder(ItemSugarStack.class)
            .vanillaItem(VanillaItemId.SUGAR)
            .build();
  }
}
