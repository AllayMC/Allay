package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc11Stack extends ItemStack {
  ItemType<ItemMusicDisc11Stack> MUSIC_DISC_11_TYPE = ItemTypeBuilder
          .builder(ItemMusicDisc11Stack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_11)
          .build();
}
