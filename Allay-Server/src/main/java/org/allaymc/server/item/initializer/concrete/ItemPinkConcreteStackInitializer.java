package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemPinkConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkConcreteStackInitializer {
  static void init() {
    ItemTypes.PINK_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemPinkConcreteStack.class)
            .vanillaItem(VanillaItemId.PINK_CONCRETE)
            .build();
  }
}
