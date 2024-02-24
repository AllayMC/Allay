package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMuttonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMuttonStackInitializer {
  static void init() {
    ItemTypes.MUTTON_TYPE = ItemTypeBuilder
            .builder(ItemMuttonStack.class)
            .vanillaItem(VanillaItemId.MUTTON)
            .build();
  }
}
