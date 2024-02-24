package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemElytraStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElytraStackInitializer {
  static void init() {
    ItemTypes.ELYTRA_TYPE = ItemTypeBuilder
            .builder(ItemElytraStack.class)
            .vanillaItem(VanillaItemId.ELYTRA)
            .build();
  }
}
