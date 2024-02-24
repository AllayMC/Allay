package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemQuartzBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzBlockStackInitializer {
  static void init() {
    ItemTypes.QUARTZ_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemQuartzBlockStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_BLOCK)
            .build();
  }
}
