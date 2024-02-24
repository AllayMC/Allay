package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPaperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPaperStackInitializer {
  static void init() {
    ItemTypes.PAPER_TYPE = ItemTypeBuilder
            .builder(ItemPaperStack.class)
            .vanillaItem(VanillaItemId.PAPER)
            .build();
  }
}
