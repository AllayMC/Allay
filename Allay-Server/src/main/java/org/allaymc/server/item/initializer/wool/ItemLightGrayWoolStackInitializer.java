package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemLightGrayWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayWoolStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayWoolStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_WOOL)
            .build();
  }
}
