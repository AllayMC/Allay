package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemCrimsonFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonFenceGateStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonFenceGateStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_FENCE_GATE)
            .build();
  }
}
