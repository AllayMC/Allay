package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscFarStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscFarStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_FAR_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscFarStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_FAR)
            .build();
  }
}
