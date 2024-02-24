package org.allaymc.server.item.initializer.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.minecart.ItemCommandBlockMinecartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCommandBlockMinecartStackInitializer {
  static void init() {
    ItemTypes.COMMAND_BLOCK_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemCommandBlockMinecartStack.class)
            .vanillaItem(VanillaItemId.COMMAND_BLOCK_MINECART)
            .build();
  }
}
