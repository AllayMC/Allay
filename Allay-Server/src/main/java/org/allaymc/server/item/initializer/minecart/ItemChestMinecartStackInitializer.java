package org.allaymc.server.item.initializer.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.minecart.ItemChestMinecartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChestMinecartStackInitializer {
  static void init() {
    ItemTypes.CHEST_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemChestMinecartStack.class)
            .vanillaItem(VanillaItemId.CHEST_MINECART)
            .build();
  }
}
