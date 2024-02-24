package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemQuartzStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzStackInitializer {
  static void init() {
    ItemTypes.QUARTZ_TYPE = ItemTypeBuilder
            .builder(ItemQuartzStack.class)
            .vanillaItem(VanillaItemId.QUARTZ)
            .build();
  }
}
