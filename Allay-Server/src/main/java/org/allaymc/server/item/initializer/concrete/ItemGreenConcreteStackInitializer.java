package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemGreenConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenConcreteStackInitializer {
  static void init() {
    ItemTypes.GREEN_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemGreenConcreteStack.class)
            .vanillaItem(VanillaItemId.GREEN_CONCRETE)
            .build();
  }
}
