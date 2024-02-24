package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscWaitStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWaitStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_WAIT_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscWaitStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_WAIT)
            .build();
  }
}
