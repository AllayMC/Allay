package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemOxidizedCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCopperGrateStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCopperGrateStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_COPPER_GRATE)
            .build();
  }
}
