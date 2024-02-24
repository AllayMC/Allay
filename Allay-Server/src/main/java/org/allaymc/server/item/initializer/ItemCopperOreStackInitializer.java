package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCopperOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperOreStackInitializer {
  static void init() {
    ItemTypes.COPPER_ORE_TYPE = ItemTypeBuilder
            .builder(ItemCopperOreStack.class)
            .vanillaItem(VanillaItemId.COPPER_ORE)
            .build();
  }
}
