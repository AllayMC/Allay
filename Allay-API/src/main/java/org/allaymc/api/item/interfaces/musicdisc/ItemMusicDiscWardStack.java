package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscWardStack extends ItemStack {
  ItemType<ItemMusicDiscWardStack> MUSIC_DISC_WARD_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscWardStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_WARD)
          .build();
}
