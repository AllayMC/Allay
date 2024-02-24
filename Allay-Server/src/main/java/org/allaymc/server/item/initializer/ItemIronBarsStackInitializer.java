package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronBarsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronBarsStackInitializer {
  static void init() {
    ItemTypes.IRON_BARS_TYPE = ItemTypeBuilder
            .builder(ItemIronBarsStack.class)
            .vanillaItem(VanillaItemId.IRON_BARS)
            .build();
  }
}
