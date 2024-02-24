package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPrismarineStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineStackInitializer {
  static void init() {
    ItemTypes.PRISMARINE_TYPE = ItemTypeBuilder
            .builder(ItemPrismarineStack.class)
            .vanillaItem(VanillaItemId.PRISMARINE)
            .build();
  }
}
