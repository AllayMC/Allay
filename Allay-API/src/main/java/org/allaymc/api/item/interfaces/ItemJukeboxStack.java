package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJukeboxStack extends ItemStack {
  ItemType<ItemJukeboxStack> JUKEBOX_TYPE = ItemTypeBuilder
          .builder(ItemJukeboxStack.class)
          .vanillaItem(VanillaItemId.JUKEBOX)
          .build();
}
