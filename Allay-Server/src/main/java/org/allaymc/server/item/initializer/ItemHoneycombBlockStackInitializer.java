package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHoneycombBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoneycombBlockStackInitializer {
  static void init() {
    ItemTypes.HONEYCOMB_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemHoneycombBlockStack.class)
            .vanillaItem(VanillaItemId.HONEYCOMB_BLOCK)
            .build();
  }
}
