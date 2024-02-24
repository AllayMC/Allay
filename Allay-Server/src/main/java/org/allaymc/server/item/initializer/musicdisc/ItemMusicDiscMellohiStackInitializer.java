package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscMellohiStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMellohiStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_MELLOHI_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscMellohiStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_MELLOHI)
            .build();
  }
}
