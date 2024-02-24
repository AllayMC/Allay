package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemBirchFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchFenceGateStackInitializer {
  static void init() {
    ItemTypes.BIRCH_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemBirchFenceGateStack.class)
            .vanillaItem(VanillaItemId.BIRCH_FENCE_GATE)
            .build();
  }
}
