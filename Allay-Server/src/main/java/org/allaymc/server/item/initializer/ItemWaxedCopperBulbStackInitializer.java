package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperBulbStackInitializer {
  static void init() {
    ItemTypes.WAXED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCopperBulbStack.class)
            .vanillaItem(VanillaItemId.WAXED_COPPER_BULB)
            .build();
  }
}
