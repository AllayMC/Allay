package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc5Stack extends ItemStack {
  ItemType<ItemMusicDisc5Stack> MUSIC_DISC_5_TYPE = ItemTypeBuilder
          .builder(ItemMusicDisc5Stack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_5)
          .build();
}
