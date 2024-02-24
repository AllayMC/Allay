package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemSpruceFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceFenceGateStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemSpruceFenceGateStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_FENCE_GATE)
            .build();
  }
}
