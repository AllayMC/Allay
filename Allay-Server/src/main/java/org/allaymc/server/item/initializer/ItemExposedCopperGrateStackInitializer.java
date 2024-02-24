package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemExposedCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperGrateStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemExposedCopperGrateStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_COPPER_GRATE)
            .build();
  }
}
