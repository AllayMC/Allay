package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateCopperOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateCopperOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_COPPER_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateCopperOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_COPPER_ORE)
            .build();
  }
}
