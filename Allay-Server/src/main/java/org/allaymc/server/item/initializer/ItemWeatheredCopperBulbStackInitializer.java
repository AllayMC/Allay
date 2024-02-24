package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWeatheredCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCopperBulbStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCopperBulbStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_COPPER_BULB)
            .build();
  }
}
