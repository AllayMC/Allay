package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSandstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSandstoneStackInitializer {
  static void init() {
    ItemTypes.SANDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemSandstoneStack.class)
            .vanillaItem(VanillaItemId.SANDSTONE)
            .build();
  }
}
