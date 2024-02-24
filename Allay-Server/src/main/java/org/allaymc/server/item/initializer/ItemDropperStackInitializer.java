package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDropperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDropperStackInitializer {
  static void init() {
    ItemTypes.DROPPER_TYPE = ItemTypeBuilder
            .builder(ItemDropperStack.class)
            .vanillaItem(VanillaItemId.DROPPER)
            .build();
  }
}
