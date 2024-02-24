package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemOrangeConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeConcreteStackInitializer {
  static void init() {
    ItemTypes.ORANGE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemOrangeConcreteStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CONCRETE)
            .build();
  }
}
