package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCoalOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalOreStackInitializer {
  static void init() {
    ItemTypes.COAL_ORE_TYPE = ItemTypeBuilder
            .builder(ItemCoalOreStack.class)
            .vanillaItem(VanillaItemId.COAL_ORE)
            .build();
  }
}
