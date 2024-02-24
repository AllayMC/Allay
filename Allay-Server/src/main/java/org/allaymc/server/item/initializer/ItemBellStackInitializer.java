package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBellStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBellStackInitializer {
  static void init() {
    ItemTypes.BELL_TYPE = ItemTypeBuilder
            .builder(ItemBellStack.class)
            .vanillaItem(VanillaItemId.BELL)
            .build();
  }
}
