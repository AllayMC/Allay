package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSeagrassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeagrassStackInitializer {
  static void init() {
    ItemTypes.SEAGRASS_TYPE = ItemTypeBuilder
            .builder(ItemSeagrassStack.class)
            .vanillaItem(VanillaItemId.SEAGRASS)
            .build();
  }
}
