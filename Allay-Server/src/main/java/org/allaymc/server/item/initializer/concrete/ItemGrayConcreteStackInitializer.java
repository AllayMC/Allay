package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemGrayConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayConcreteStackInitializer {
  static void init() {
    ItemTypes.GRAY_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemGrayConcreteStack.class)
            .vanillaItem(VanillaItemId.GRAY_CONCRETE)
            .build();
  }
}
