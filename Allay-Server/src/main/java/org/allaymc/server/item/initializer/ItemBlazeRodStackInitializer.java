package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlazeRodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazeRodStackInitializer {
  static void init() {
    ItemTypes.BLAZE_ROD_TYPE = ItemTypeBuilder
            .builder(ItemBlazeRodStack.class)
            .vanillaItem(VanillaItemId.BLAZE_ROD)
            .build();
  }
}
