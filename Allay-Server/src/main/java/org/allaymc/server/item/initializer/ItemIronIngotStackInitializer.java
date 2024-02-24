package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronIngotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronIngotStackInitializer {
  static void init() {
    ItemTypes.IRON_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemIronIngotStack.class)
            .vanillaItem(VanillaItemId.IRON_INGOT)
            .build();
  }
}
