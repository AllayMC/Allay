package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFishingRodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFishingRodStackInitializer {
  static void init() {
    ItemTypes.FISHING_ROD_TYPE = ItemTypeBuilder
            .builder(ItemFishingRodStack.class)
            .vanillaItem(VanillaItemId.FISHING_ROD)
            .build();
  }
}
