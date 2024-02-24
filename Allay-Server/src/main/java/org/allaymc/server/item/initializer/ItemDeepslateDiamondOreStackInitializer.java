package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateDiamondOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateDiamondOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_DIAMOND_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateDiamondOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_DIAMOND_ORE)
            .build();
  }
}
