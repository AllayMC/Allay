package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemDeadBubbleCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadBubbleCoralStackInitializer {
  static void init() {
    ItemTypes.DEAD_BUBBLE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadBubbleCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_BUBBLE_CORAL)
            .build();
  }
}
