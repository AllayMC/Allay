package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscChirpStack extends ItemStack {
  ItemType<ItemMusicDiscChirpStack> MUSIC_DISC_CHIRP_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscChirpStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_CHIRP)
          .build();
}
