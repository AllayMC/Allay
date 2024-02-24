package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedSandstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedSandstoneStackInitializer {
  static void init() {
    ItemTypes.RED_SANDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemRedSandstoneStack.class)
            .vanillaItem(VanillaItemId.RED_SANDSTONE)
            .build();
  }
}
