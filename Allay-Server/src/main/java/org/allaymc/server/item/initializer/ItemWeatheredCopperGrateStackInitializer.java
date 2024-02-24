package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWeatheredCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCopperGrateStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCopperGrateStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_COPPER_GRATE)
            .build();
  }
}
