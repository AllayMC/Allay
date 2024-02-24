package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAmethystBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAmethystBlockStackInitializer {
  static void init() {
    ItemTypes.AMETHYST_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemAmethystBlockStack.class)
            .vanillaItem(VanillaItemId.AMETHYST_BLOCK)
            .build();
  }
}
