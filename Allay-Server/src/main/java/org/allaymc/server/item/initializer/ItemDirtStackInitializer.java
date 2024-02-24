package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDirtStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDirtStackInitializer {
  static void init() {
    ItemTypes.DIRT_TYPE = ItemTypeBuilder
            .builder(ItemDirtStack.class)
            .vanillaItem(VanillaItemId.DIRT)
            .build();
  }
}
