package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNoteblockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNoteblockStackInitializer {
  static void init() {
    ItemTypes.NOTEBLOCK_TYPE = ItemTypeBuilder
            .builder(ItemNoteblockStack.class)
            .vanillaItem(VanillaItemId.NOTEBLOCK)
            .build();
  }
}
