package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscBlocksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscBlocksStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_BLOCKS_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscBlocksStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_BLOCKS)
            .build();
  }
}
