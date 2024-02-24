package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscStalStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscStalStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_STAL_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscStalStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_STAL)
            .build();
  }
}
