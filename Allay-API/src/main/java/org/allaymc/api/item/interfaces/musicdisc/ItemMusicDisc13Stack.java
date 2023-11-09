package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc13Stack extends ItemStack {
  ItemType<ItemMusicDisc13Stack> MUSIC_DISC_13_TYPE = ItemTypeBuilder
          .builder(ItemMusicDisc13Stack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_13)
          .build();
}
