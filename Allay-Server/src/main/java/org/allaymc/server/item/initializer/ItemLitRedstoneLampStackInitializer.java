package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitRedstoneLampStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitRedstoneLampStackInitializer {
  static void init() {
    ItemTypes.LIT_REDSTONE_LAMP_TYPE = ItemTypeBuilder
            .builder(ItemLitRedstoneLampStack.class)
            .vanillaItem(VanillaItemId.LIT_REDSTONE_LAMP)
            .build();
  }
}
