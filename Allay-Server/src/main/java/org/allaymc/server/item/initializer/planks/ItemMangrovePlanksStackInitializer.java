package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemMangrovePlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangrovePlanksStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemMangrovePlanksStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_PLANKS)
            .build();
  }
}
