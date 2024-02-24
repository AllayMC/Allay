package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBedStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBedStackInitializer {
  static void init() {
    ItemTypes.BED_TYPE = ItemTypeBuilder
            .builder(ItemBedStack.class)
            .vanillaItem(VanillaItemId.BED)
            .build();
  }
}
