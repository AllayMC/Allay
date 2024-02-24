package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConcreteStackInitializer {
  static void init() {
    ItemTypes.CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemConcreteStack.class)
            .vanillaItem(VanillaItemId.CONCRETE)
            .build();
  }
}
