package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBowStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBowStackInitializer {
  static void init() {
    ItemTypes.BOW_TYPE = ItemTypeBuilder
            .builder(ItemBowStack.class)
            .vanillaItem(VanillaItemId.BOW)
            .build();
  }
}
