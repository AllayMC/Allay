package org.allaymc.server.item.initializer.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leaves.ItemAzaleaLeavesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaLeavesStackInitializer {
  static void init() {
    ItemTypes.AZALEA_LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemAzaleaLeavesStack.class)
            .vanillaItem(VanillaItemId.AZALEA_LEAVES)
            .build();
  }
}
