package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemWhiteConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteConcreteStackInitializer {
  static void init() {
    ItemTypes.WHITE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteConcreteStack.class)
            .vanillaItem(VanillaItemId.WHITE_CONCRETE)
            .build();
  }
}
