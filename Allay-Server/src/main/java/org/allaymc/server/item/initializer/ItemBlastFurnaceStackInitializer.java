package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlastFurnaceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlastFurnaceStackInitializer {
  static void init() {
    ItemTypes.BLAST_FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemBlastFurnaceStack.class)
            .vanillaItem(VanillaItemId.BLAST_FURNACE)
            .build();
  }
}
