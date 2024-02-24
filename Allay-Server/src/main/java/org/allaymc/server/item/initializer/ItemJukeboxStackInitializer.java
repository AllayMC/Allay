package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemJukeboxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJukeboxStackInitializer {
  static void init() {
    ItemTypes.JUKEBOX_TYPE = ItemTypeBuilder
            .builder(ItemJukeboxStack.class)
            .vanillaItem(VanillaItemId.JUKEBOX)
            .build();
  }
}
