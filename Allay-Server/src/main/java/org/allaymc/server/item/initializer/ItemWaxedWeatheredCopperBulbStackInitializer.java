package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedWeatheredCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperBulbStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopperBulbStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER_BULB)
            .build();
  }
}
