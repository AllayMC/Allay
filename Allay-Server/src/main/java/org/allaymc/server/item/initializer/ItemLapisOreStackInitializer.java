package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLapisOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLapisOreStackInitializer {
  static void init() {
    ItemTypes.LAPIS_ORE_TYPE = ItemTypeBuilder
            .builder(ItemLapisOreStack.class)
            .vanillaItem(VanillaItemId.LAPIS_ORE)
            .build();
  }
}
