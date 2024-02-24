package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemBubbleCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBubbleCoralStackInitializer {
  static void init() {
    ItemTypes.BUBBLE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemBubbleCoralStack.class)
            .vanillaItem(VanillaItemId.BUBBLE_CORAL)
            .build();
  }
}
