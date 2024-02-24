package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.CUT_COPPER_STAIRS)
            .build();
  }
}
