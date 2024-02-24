package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTorchflowerSeedsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchflowerSeedsStackInitializer {
  static void init() {
    ItemTypes.TORCHFLOWER_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemTorchflowerSeedsStack.class)
            .vanillaItem(VanillaItemId.TORCHFLOWER_SEEDS)
            .build();
  }
}
