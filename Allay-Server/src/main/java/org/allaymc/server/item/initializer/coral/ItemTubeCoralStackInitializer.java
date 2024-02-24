package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemTubeCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTubeCoralStackInitializer {
  static void init() {
    ItemTypes.TUBE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemTubeCoralStack.class)
            .vanillaItem(VanillaItemId.TUBE_CORAL)
            .build();
  }
}
