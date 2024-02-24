package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedstoneTorchStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneTorchStackInitializer {
  static void init() {
    ItemTypes.REDSTONE_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemRedstoneTorchStack.class)
            .vanillaItem(VanillaItemId.REDSTONE_TORCH)
            .build();
  }
}
