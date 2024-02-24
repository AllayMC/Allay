package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemBlackConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackConcreteStackInitializer {
  static void init() {
    ItemTypes.BLACK_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemBlackConcreteStack.class)
            .vanillaItem(VanillaItemId.BLACK_CONCRETE)
            .build();
  }
}
