package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEndRodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndRodStackInitializer {
  static void init() {
    ItemTypes.END_ROD_TYPE = ItemTypeBuilder
            .builder(ItemEndRodStack.class)
            .vanillaItem(VanillaItemId.END_ROD)
            .build();
  }
}
