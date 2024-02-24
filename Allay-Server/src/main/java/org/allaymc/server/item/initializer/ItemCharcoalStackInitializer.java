package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCharcoalStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCharcoalStackInitializer {
  static void init() {
    ItemTypes.CHARCOAL_TYPE = ItemTypeBuilder
            .builder(ItemCharcoalStack.class)
            .vanillaItem(VanillaItemId.CHARCOAL)
            .build();
  }
}
