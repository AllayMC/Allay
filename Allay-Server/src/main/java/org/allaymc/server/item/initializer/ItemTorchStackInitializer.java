package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTorchStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchStackInitializer {
  static void init() {
    ItemTypes.TORCH_TYPE = ItemTypeBuilder
            .builder(ItemTorchStack.class)
            .vanillaItem(VanillaItemId.TORCH)
            .build();
  }
}
