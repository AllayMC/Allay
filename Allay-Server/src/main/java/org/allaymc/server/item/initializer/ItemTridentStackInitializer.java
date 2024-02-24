package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTridentStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTridentStackInitializer {
  static void init() {
    ItemTypes.TRIDENT_TYPE = ItemTypeBuilder
            .builder(ItemTridentStack.class)
            .vanillaItem(VanillaItemId.TRIDENT)
            .build();
  }
}
