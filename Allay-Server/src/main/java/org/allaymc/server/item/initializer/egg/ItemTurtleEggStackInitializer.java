package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemTurtleEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleEggStackInitializer {
  static void init() {
    ItemTypes.TURTLE_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTurtleEggStack.class)
            .vanillaItem(VanillaItemId.TURTLE_EGG)
            .build();
  }
}
