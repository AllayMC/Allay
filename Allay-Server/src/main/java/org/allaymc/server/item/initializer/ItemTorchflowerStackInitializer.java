package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTorchflowerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchflowerStackInitializer {
  static void init() {
    ItemTypes.TORCHFLOWER_TYPE = ItemTypeBuilder
            .builder(ItemTorchflowerStack.class)
            .vanillaItem(VanillaItemId.TORCHFLOWER)
            .build();
  }
}
