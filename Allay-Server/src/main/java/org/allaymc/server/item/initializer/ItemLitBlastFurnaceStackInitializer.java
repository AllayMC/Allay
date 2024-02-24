package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitBlastFurnaceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitBlastFurnaceStackInitializer {
  static void init() {
    ItemTypes.LIT_BLAST_FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemLitBlastFurnaceStack.class)
            .vanillaItem(VanillaItemId.LIT_BLAST_FURNACE)
            .build();
  }
}
