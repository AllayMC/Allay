package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscChirpStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscChirpStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_CHIRP_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscChirpStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_CHIRP)
            .build();
  }
}
