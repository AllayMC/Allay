package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemJigsawStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJigsawStackInitializer {
  static void init() {
    ItemTypes.JIGSAW_TYPE = ItemTypeBuilder
            .builder(ItemJigsawStack.class)
            .vanillaItem(VanillaItemId.JIGSAW)
            .build();
  }
}
