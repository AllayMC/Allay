package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPinkPetalsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkPetalsStackInitializer {
  static void init() {
    ItemTypes.PINK_PETALS_TYPE = ItemTypeBuilder
            .builder(ItemPinkPetalsStack.class)
            .vanillaItem(VanillaItemId.PINK_PETALS)
            .build();
  }
}
