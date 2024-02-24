package org.allaymc.server.item.initializer.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.musicdisc.ItemMusicDiscPigstepStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscPigstepStackInitializer {
  static void init() {
    ItemTypes.MUSIC_DISC_PIGSTEP_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscPigstepStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_PIGSTEP)
            .build();
  }
}
