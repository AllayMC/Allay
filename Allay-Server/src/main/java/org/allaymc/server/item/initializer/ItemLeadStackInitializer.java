package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLeadStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeadStackInitializer {
  static void init() {
    ItemTypes.LEAD_TYPE = ItemTypeBuilder
            .builder(ItemLeadStack.class)
            .vanillaItem(VanillaItemId.LEAD)
            .build();
  }
}
