package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLapisBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLapisBlockStackInitializer {
  static void init() {
    ItemTypes.LAPIS_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemLapisBlockStack.class)
            .vanillaItem(VanillaItemId.LAPIS_BLOCK)
            .build();
  }
}
