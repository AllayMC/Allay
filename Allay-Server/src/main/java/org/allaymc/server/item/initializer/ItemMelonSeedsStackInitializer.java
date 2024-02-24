package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMelonSeedsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonSeedsStackInitializer {
  static void init() {
    ItemTypes.MELON_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemMelonSeedsStack.class)
            .vanillaItem(VanillaItemId.MELON_SEEDS)
            .build();
  }
}
