package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscPigstepStack extends ItemStack {
  ItemType<ItemMusicDiscPigstepStack> MUSIC_DISC_PIGSTEP_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscPigstepStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_PIGSTEP)
          .build();
}
