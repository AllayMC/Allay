package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBlueDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueDyeStackInitializer {
  static void init() {
    ItemTypes.BLUE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemBlueDyeStack.class)
            .vanillaItem(VanillaItemId.BLUE_DYE)
            .build();
  }
}
