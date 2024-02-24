package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMossBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossBlockStackInitializer {
  static void init() {
    ItemTypes.MOSS_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemMossBlockStack.class)
            .vanillaItem(VanillaItemId.MOSS_BLOCK)
            .build();
  }
}
