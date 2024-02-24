package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemGrayWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayWoolStackInitializer {
  static void init() {
    ItemTypes.GRAY_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemGrayWoolStack.class)
            .vanillaItem(VanillaItemId.GRAY_WOOL)
            .build();
  }
}
