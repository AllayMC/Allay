package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMallStack extends ItemStack {
  ItemType<ItemMusicDiscMallStack> MUSIC_DISC_MALL_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscMallStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_MALL)
          .build();
}
