package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGrindstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrindstoneStackInitializer {
  static void init() {
    ItemTypes.GRINDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemGrindstoneStack.class)
            .vanillaItem(VanillaItemId.GRINDSTONE)
            .build();
  }
}
