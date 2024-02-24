package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLadderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLadderStackInitializer {
  static void init() {
    ItemTypes.LADDER_TYPE = ItemTypeBuilder
            .builder(ItemLadderStack.class)
            .vanillaItem(VanillaItemId.LADDER)
            .build();
  }
}
