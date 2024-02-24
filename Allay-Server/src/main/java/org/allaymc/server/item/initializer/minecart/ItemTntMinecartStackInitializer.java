package org.allaymc.server.item.initializer.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.minecart.ItemTntMinecartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTntMinecartStackInitializer {
  static void init() {
    ItemTypes.TNT_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemTntMinecartStack.class)
            .vanillaItem(VanillaItemId.TNT_MINECART)
            .build();
  }
}
