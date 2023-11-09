package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscFarStack extends ItemStack {
  ItemType<ItemMusicDiscFarStack> MUSIC_DISC_FAR_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscFarStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_FAR)
          .build();
}
