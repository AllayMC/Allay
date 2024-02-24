package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscWardStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWardStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_WARD_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscWardStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_WARD)
            .build();
  }
}
