package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAllowStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAllowStackInitializer {
  static void init() {
    ItemTypes.ALLOW_TYPE = ItemTypeBuilder
            .builder(ItemAllowStack.class)
            .vanillaItem(VanillaItemId.ALLOW)
            .build();
  }
}
