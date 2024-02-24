package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStructureBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStructureBlockStackInitializer {
  static void init() {
    ItemTypes.STRUCTURE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemStructureBlockStack.class)
            .vanillaItem(VanillaItemId.STRUCTURE_BLOCK)
            .build();
  }
}
