package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperGrateStackInitializer {
  static void init() {
    ItemTypes.COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemCopperGrateStack.class)
            .vanillaItem(VanillaItemId.COPPER_GRATE)
            .build();
  }
}
