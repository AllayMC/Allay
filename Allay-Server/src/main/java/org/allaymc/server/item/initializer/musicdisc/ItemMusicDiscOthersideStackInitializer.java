package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscOthersideStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscOthersideStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_OTHERSIDE_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscOthersideStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_OTHERSIDE)
            .build();
  }
}
