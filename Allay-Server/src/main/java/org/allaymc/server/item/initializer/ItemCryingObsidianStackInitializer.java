package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCryingObsidianStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCryingObsidianStackInitializer {
  static void init() {
    ItemTypes.CRYING_OBSIDIAN_TYPE = ItemTypeBuilder
            .builder(ItemCryingObsidianStack.class)
            .vanillaItem(VanillaItemId.CRYING_OBSIDIAN)
            .build();
  }
}
