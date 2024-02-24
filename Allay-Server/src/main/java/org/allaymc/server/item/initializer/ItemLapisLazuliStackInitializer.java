package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLapisLazuliStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLapisLazuliStackInitializer {
  static void init() {
    ItemTypes.LAPIS_LAZULI_TYPE = ItemTypeBuilder
            .builder(ItemLapisLazuliStack.class)
            .vanillaItem(VanillaItemId.LAPIS_LAZULI)
            .build();
  }
}
