package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEnderPearlStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderPearlStackInitializer {
  static void init() {
    ItemTypes.ENDER_PEARL_TYPE = ItemTypeBuilder
            .builder(ItemEnderPearlStack.class)
            .vanillaItem(VanillaItemId.ENDER_PEARL)
            .build();
  }
}
