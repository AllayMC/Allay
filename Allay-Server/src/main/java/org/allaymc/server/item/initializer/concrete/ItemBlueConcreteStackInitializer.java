package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemBlueConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueConcreteStackInitializer {
  static void init() {
    ItemTypes.BLUE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemBlueConcreteStack.class)
            .vanillaItem(VanillaItemId.BLUE_CONCRETE)
            .build();
  }
}
