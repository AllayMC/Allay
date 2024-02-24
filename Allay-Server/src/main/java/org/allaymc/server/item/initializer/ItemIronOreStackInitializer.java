package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronOreStackInitializer {
  static void init() {
    ItemTypes.IRON_ORE_TYPE = ItemTypeBuilder
            .builder(ItemIronOreStack.class)
            .vanillaItem(VanillaItemId.IRON_ORE)
            .build();
  }
}
