package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSculkStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkStackInitializer {
  static void init() {
    ItemTypes.SCULK_TYPE = ItemTypeBuilder
            .builder(ItemSculkStack.class)
            .vanillaItem(VanillaItemId.SCULK)
            .build();
  }
}
