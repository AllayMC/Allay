package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemUnderwaterTorchStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnderwaterTorchStackInitializer {
  static void init() {
    ItemTypes.UNDERWATER_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemUnderwaterTorchStack.class)
            .vanillaItem(VanillaItemId.UNDERWATER_TORCH)
            .build();
  }
}
