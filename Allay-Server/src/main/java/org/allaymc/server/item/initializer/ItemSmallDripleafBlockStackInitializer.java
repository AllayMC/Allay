package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSmallDripleafBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmallDripleafBlockStackInitializer {
  static void init() {
    ItemTypes.SMALL_DRIPLEAF_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemSmallDripleafBlockStack.class)
            .vanillaItem(VanillaItemId.SMALL_DRIPLEAF_BLOCK)
            .build();
  }
}
