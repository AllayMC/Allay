package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemClayBallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClayBallStackInitializer {
  static void init() {
    ItemTypes.CLAY_BALL_TYPE = ItemTypeBuilder
            .builder(ItemClayBallStack.class)
            .vanillaItem(VanillaItemId.CLAY_BALL)
            .build();
  }
}
