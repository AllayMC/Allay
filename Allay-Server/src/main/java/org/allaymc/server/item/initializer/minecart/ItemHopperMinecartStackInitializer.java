package org.allaymc.server.item.initializer.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.minecart.ItemHopperMinecartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHopperMinecartStackInitializer {
  static void init() {
    ItemTypes.HOPPER_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemHopperMinecartStack.class)
            .vanillaItem(VanillaItemId.HOPPER_MINECART)
            .build();
  }
}
