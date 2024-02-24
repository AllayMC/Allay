package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemCyanConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanConcreteStackInitializer {
  static void init() {
    ItemTypes.CYAN_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemCyanConcreteStack.class)
            .vanillaItem(VanillaItemId.CYAN_CONCRETE)
            .build();
  }
}
