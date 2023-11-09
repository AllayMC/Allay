package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscCatStack extends ItemStack {
  ItemType<ItemMusicDiscCatStack> MUSIC_DISC_CAT_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscCatStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_CAT)
          .build();
}
