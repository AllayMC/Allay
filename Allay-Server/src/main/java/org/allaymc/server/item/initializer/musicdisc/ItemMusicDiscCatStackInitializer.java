package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscCatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscCatStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_CAT_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscCatStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_CAT)
            .build();
  }
}
