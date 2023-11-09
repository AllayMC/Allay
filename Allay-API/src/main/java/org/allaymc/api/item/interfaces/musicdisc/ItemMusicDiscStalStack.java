package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscStalStack extends ItemStack {
  ItemType<ItemMusicDiscStalStack> MUSIC_DISC_STAL_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscStalStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_STAL)
          .build();
}
