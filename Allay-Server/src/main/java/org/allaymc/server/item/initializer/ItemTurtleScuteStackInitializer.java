package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTurtleScuteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleScuteStackInitializer {
  static void init() {
    ItemTypes.TURTLE_SCUTE_TYPE = ItemTypeBuilder
            .builder(ItemTurtleScuteStack.class)
            .vanillaItem(VanillaItemId.TURTLE_SCUTE)
            .build();
  }
}
