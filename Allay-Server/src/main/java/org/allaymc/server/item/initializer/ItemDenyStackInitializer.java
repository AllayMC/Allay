package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDenyStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDenyStackInitializer {
  static void init() {
    ItemTypes.DENY_TYPE = ItemTypeBuilder
            .builder(ItemDenyStack.class)
            .vanillaItem(VanillaItemId.DENY)
            .build();
  }
}
