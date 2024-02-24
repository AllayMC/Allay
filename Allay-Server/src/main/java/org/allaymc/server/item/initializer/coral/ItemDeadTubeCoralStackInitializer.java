package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemDeadTubeCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadTubeCoralStackInitializer {
  static void init() {
    ItemTypes.DEAD_TUBE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadTubeCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_TUBE_CORAL)
            .build();
  }
}
