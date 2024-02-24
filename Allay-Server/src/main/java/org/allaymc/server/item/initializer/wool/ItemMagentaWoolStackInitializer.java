package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemMagentaWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaWoolStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemMagentaWoolStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_WOOL)
            .build();
  }
}
