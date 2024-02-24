package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPumpkinSeedsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinSeedsStackInitializer {
  static void init() {
    ItemTypes.PUMPKIN_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinSeedsStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN_SEEDS)
            .build();
  }
}
