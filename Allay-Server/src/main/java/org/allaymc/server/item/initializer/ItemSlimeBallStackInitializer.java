package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSlimeBallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSlimeBallStackInitializer {
  static void init() {
    ItemTypes.SLIME_BALL_TYPE = ItemTypeBuilder
            .builder(ItemSlimeBallStack.class)
            .vanillaItem(VanillaItemId.SLIME_BALL)
            .build();
  }
}
