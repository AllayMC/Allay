package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscStradStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscStradStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_STRAD_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscStradStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_STRAD)
            .build();
  }
}
