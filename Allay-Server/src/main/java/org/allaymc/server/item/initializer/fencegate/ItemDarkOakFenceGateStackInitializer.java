package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemDarkOakFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakFenceGateStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakFenceGateStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_FENCE_GATE)
            .build();
  }
}
