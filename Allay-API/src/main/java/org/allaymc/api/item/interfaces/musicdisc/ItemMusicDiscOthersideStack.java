package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscOthersideStack extends ItemStack {
  ItemType<ItemMusicDiscOthersideStack> MUSIC_DISC_OTHERSIDE_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscOthersideStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_OTHERSIDE)
          .build();
}
