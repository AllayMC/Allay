package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSculkVeinStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkVeinStackInitializer {
  static void init() {
    ItemTypes.SCULK_VEIN_TYPE = ItemTypeBuilder
            .builder(ItemSculkVeinStack.class)
            .vanillaItem(VanillaItemId.SCULK_VEIN)
            .build();
  }
}
