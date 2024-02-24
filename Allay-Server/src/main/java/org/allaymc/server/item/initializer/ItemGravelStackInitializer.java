package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGravelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGravelStackInitializer {
  static void init() {
    ItemTypes.GRAVEL_TYPE = ItemTypeBuilder
            .builder(ItemGravelStack.class)
            .vanillaItem(VanillaItemId.GRAVEL)
            .build();
  }
}
