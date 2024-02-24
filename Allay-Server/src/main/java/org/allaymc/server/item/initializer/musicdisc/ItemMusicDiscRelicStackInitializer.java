package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscRelicStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscRelicStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_RELIC_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscRelicStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_RELIC)
            .build();
  }
}
