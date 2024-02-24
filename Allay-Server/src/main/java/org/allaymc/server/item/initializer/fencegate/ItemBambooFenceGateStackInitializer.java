package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemBambooFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooFenceGateStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemBambooFenceGateStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_FENCE_GATE)
            .build();
  }
}
