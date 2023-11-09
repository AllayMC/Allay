package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMellohiStack extends ItemStack {
  ItemType<ItemMusicDiscMellohiStack> MUSIC_DISC_MELLOHI_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscMellohiStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_MELLOHI)
          .build();
}
