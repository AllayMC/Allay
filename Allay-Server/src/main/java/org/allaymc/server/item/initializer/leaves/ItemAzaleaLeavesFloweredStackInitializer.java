package org.allaymc.server.item.initializer.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leaves.ItemAzaleaLeavesFloweredStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaLeavesFloweredStackInitializer {
  static void init() {
    ItemTypes.AZALEA_LEAVES_FLOWERED_TYPE = ItemTypeBuilder
            .builder(ItemAzaleaLeavesFloweredStack.class)
            .vanillaItem(VanillaItemId.AZALEA_LEAVES_FLOWERED)
            .build();
  }
}
