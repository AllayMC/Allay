package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemLightBlueWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueWoolStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueWoolStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_WOOL)
            .build();
  }
}
