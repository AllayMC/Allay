package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemRedConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedConcreteStackInitializer {
  static void init() {
    ItemTypes.RED_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemRedConcreteStack.class)
            .vanillaItem(VanillaItemId.RED_CONCRETE)
            .build();
  }
}
