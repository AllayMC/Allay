package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedstoneLampStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneLampStackInitializer {
  static void init() {
    ItemTypes.REDSTONE_LAMP_TYPE = ItemTypeBuilder
            .builder(ItemRedstoneLampStack.class)
            .vanillaItem(VanillaItemId.REDSTONE_LAMP)
            .build();
  }
}
