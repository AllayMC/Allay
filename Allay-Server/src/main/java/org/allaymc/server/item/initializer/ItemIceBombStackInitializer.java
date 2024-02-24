package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIceBombStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIceBombStackInitializer {
  static void init() {
    ItemTypes.ICE_BOMB_TYPE = ItemTypeBuilder
            .builder(ItemIceBombStack.class)
            .vanillaItem(VanillaItemId.ICE_BOMB)
            .build();
  }
}
