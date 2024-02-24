package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPorkchopStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPorkchopStackInitializer {
  static void init() {
    ItemTypes.PORKCHOP_TYPE = ItemTypeBuilder
            .builder(ItemPorkchopStack.class)
            .vanillaItem(VanillaItemId.PORKCHOP)
            .build();
  }
}
