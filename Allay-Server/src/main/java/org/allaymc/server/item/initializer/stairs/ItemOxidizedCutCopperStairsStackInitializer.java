package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemOxidizedCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_CUT_COPPER_STAIRS)
            .build();
  }
}
