package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBalloonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBalloonStackInitializer {
  static void init() {
    ItemTypes.BALLOON_TYPE = ItemTypeBuilder
            .builder(ItemBalloonStack.class)
            .vanillaItem(VanillaItemId.BALLOON)
            .build();
  }
}
