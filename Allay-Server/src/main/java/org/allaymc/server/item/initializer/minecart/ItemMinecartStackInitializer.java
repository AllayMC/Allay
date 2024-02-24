package org.allaymc.server.item.initializer.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.minecart.ItemMinecartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMinecartStackInitializer {
  static void init() {
    ItemTypes.MINECART_TYPE = ItemTypeBuilder
            .builder(ItemMinecartStack.class)
            .vanillaItem(VanillaItemId.MINECART)
            .build();
  }
}
