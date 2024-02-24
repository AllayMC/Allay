package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemUnlitRedstoneTorchStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnlitRedstoneTorchStackInitializer {
  static void init() {
    ItemTypes.UNLIT_REDSTONE_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemUnlitRedstoneTorchStack.class)
            .vanillaItem(VanillaItemId.UNLIT_REDSTONE_TORCH)
            .build();
  }
}
