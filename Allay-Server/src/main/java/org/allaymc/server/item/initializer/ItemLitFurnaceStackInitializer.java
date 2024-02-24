package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitFurnaceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitFurnaceStackInitializer {
  static void init() {
    ItemTypes.LIT_FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemLitFurnaceStack.class)
            .vanillaItem(VanillaItemId.LIT_FURNACE)
            .build();
  }
}
