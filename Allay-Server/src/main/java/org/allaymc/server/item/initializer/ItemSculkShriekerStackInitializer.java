package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSculkShriekerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkShriekerStackInitializer {
  static void init() {
    ItemTypes.SCULK_SHRIEKER_TYPE = ItemTypeBuilder
            .builder(ItemSculkShriekerStack.class)
            .vanillaItem(VanillaItemId.SCULK_SHRIEKER)
            .build();
  }
}
