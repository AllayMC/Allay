package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemOxidizedCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCopperBulbStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCopperBulbStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_COPPER_BULB)
            .build();
  }
}
