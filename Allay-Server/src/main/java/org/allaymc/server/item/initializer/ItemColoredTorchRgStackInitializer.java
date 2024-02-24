package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemColoredTorchRgStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemColoredTorchRgStackInitializer {
  static void init() {
    ItemTypes.COLORED_TORCH_RG_TYPE = ItemTypeBuilder
            .builder(ItemColoredTorchRgStack.class)
            .vanillaItem(VanillaItemId.COLORED_TORCH_RG)
            .build();
  }
}
