package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemLightBlueConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueConcreteStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueConcreteStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CONCRETE)
            .build();
  }
}
