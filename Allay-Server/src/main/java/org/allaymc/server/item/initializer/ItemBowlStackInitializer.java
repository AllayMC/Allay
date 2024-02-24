package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBowlStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBowlStackInitializer {
  static void init() {
    ItemTypes.BOWL_TYPE = ItemTypeBuilder
            .builder(ItemBowlStack.class)
            .vanillaItem(VanillaItemId.BOWL)
            .build();
  }
}
