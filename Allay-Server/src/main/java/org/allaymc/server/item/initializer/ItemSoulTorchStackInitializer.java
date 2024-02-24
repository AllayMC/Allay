package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSoulTorchStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulTorchStackInitializer {
  static void init() {
    ItemTypes.SOUL_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemSoulTorchStack.class)
            .vanillaItem(VanillaItemId.SOUL_TORCH)
            .build();
  }
}
