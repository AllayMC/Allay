package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemPurpleConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleConcreteStackInitializer {
  static void init() {
    ItemTypes.PURPLE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleConcreteStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CONCRETE)
            .build();
  }
}
