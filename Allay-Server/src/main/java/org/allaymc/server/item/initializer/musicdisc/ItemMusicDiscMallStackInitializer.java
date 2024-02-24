package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscMallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMallStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_MALL_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscMallStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_MALL)
            .build();
  }
}
