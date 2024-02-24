package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEndCrystalStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndCrystalStackInitializer {
  static void init() {
    ItemTypes.END_CRYSTAL_TYPE = ItemTypeBuilder
            .builder(ItemEndCrystalStack.class)
            .vanillaItem(VanillaItemId.END_CRYSTAL)
            .build();
  }
}
