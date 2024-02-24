package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldNuggetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldNuggetStackInitializer {
  static void init() {
    ItemTypes.GOLD_NUGGET_TYPE = ItemTypeBuilder
            .builder(ItemGoldNuggetStack.class)
            .vanillaItem(VanillaItemId.GOLD_NUGGET)
            .build();
  }
}
