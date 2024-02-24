package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronNuggetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronNuggetStackInitializer {
  static void init() {
    ItemTypes.IRON_NUGGET_TYPE = ItemTypeBuilder
            .builder(ItemIronNuggetStack.class)
            .vanillaItem(VanillaItemId.IRON_NUGGET)
            .build();
  }
}
