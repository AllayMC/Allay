package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemYellowConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowConcreteStackInitializer {
  static void init() {
    ItemTypes.YELLOW_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemYellowConcreteStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CONCRETE)
            .build();
  }
}
