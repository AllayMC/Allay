package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemAcaciaFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaFenceGateStackInitializer {
  static void init() {
    ItemTypes.ACACIA_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaFenceGateStack.class)
            .vanillaItem(VanillaItemId.ACACIA_FENCE_GATE)
            .build();
  }
}
