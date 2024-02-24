package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemObserverStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemObserverStackInitializer {
  static void init() {
    ItemTypes.OBSERVER_TYPE = ItemTypeBuilder
            .builder(ItemObserverStack.class)
            .vanillaItem(VanillaItemId.OBSERVER)
            .build();
  }
}
