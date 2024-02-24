package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemQuartzOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzOreStackInitializer {
  static void init() {
    ItemTypes.QUARTZ_ORE_TYPE = ItemTypeBuilder
            .builder(ItemQuartzOreStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_ORE)
            .build();
  }
}
