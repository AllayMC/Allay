package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedDeepslateTilesStack extends ItemStack {
  ItemType<ItemCrackedDeepslateTilesStack> CRACKED_DEEPSLATE_TILES_TYPE = ItemTypeBuilder
          .builder(ItemCrackedDeepslateTilesStack.class)
          .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_TILES)
          .build();
}
