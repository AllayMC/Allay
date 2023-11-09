package org.allaymc.api.item.interfaces.musicdisc;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscRelicStack extends ItemStack {
  ItemType<ItemMusicDiscRelicStack> MUSIC_DISC_RELIC_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscRelicStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_RELIC)
          .build();
}
