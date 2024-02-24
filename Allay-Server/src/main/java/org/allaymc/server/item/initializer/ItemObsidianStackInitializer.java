package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemObsidianStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemObsidianStackInitializer {
  static void init() {
    ItemTypes.OBSIDIAN_TYPE = ItemTypeBuilder
            .builder(ItemObsidianStack.class)
            .vanillaItem(VanillaItemId.OBSIDIAN)
            .build();
  }
}
