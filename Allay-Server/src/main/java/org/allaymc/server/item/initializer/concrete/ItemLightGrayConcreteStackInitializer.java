package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemLightGrayConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayConcreteStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayConcreteStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CONCRETE)
            .build();
  }
}
