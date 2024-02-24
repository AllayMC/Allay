package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemKelpStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemKelpStackInitializer {
  static void init() {
    ItemTypes.ITEM_KELP_TYPE = ItemTypeBuilder
            .builder(ItemItemKelpStack.class)
            .vanillaItem(VanillaItemId.ITEM_KELP)
            .build();
  }
}
