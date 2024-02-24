package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPortalStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPortalStackInitializer {
  static void init() {
    ItemTypes.PORTAL_TYPE = ItemTypeBuilder
            .builder(ItemPortalStack.class)
            .vanillaItem(VanillaItemId.PORTAL)
            .build();
  }
}
