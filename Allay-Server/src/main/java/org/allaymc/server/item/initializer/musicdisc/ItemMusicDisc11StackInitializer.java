package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDisc11Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc11StackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_11_TYPE = ItemTypeBuilder
            .builder(ItemMusicDisc11Stack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_11)
            .build();
  }
}
