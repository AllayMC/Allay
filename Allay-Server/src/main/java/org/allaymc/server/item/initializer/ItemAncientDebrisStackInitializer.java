package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAncientDebrisStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAncientDebrisStackInitializer {
  static void init() {
    ItemTypes.ANCIENT_DEBRIS_TYPE = ItemTypeBuilder
            .builder(ItemAncientDebrisStack.class)
            .vanillaItem(VanillaItemId.ANCIENT_DEBRIS)
            .build();
  }
}
