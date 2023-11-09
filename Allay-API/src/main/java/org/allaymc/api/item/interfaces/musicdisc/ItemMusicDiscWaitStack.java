package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWaitStack extends ItemStack {
  ItemType<ItemMusicDiscWaitStack> MUSIC_DISC_WAIT_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscWaitStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_WAIT)
          .build();
}
