package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondOreStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondOreStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_ORE)
            .build();
  }
}
