package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemKelpStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemKelpStackInitializer {
  static void init() {
    ItemTypes.KELP_TYPE = ItemTypeBuilder
            .builder(ItemKelpStack.class)
            .vanillaItem(VanillaItemId.KELP)
            .build();
  }
}
