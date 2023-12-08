package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffStack extends ItemStack {
  ItemType<ItemPolishedTuffStack> POLISHED_TUFF_TYPE = ItemTypeBuilder
          .builder(ItemPolishedTuffStack.class)
          .vanillaItem(VanillaItemId.POLISHED_TUFF)
          .build();
}
