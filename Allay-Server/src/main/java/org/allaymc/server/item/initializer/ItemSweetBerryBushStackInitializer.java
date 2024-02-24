package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSweetBerryBushStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSweetBerryBushStackInitializer {
  static void init() {
    ItemTypes.SWEET_BERRY_BUSH_TYPE = ItemTypeBuilder
            .builder(ItemSweetBerryBushStack.class)
            .vanillaItem(VanillaItemId.SWEET_BERRY_BUSH)
            .build();
  }
}
