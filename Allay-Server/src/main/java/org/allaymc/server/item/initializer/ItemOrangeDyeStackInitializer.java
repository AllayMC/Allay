package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemOrangeDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeDyeStackInitializer {
  static void init() {
    ItemTypes.ORANGE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemOrangeDyeStack.class)
            .vanillaItem(VanillaItemId.ORANGE_DYE)
            .build();
  }
}
