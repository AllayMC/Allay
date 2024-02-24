package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAndesiteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAndesiteStackInitializer {
  static void init() {
    ItemTypes.ANDESITE_TYPE = ItemTypeBuilder
            .builder(ItemAndesiteStack.class)
            .vanillaItem(VanillaItemId.ANDESITE)
            .build();
  }
}
