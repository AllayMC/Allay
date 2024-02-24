package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronHoeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronHoeStackInitializer {
  static void init() {
    ItemTypes.IRON_HOE_TYPE = ItemTypeBuilder
            .builder(ItemIronHoeStack.class)
            .vanillaItem(VanillaItemId.IRON_HOE)
            .build();
  }
}
