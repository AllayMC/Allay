package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneStack> POLISHED_BLACKSTONE_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE)
          .build();
}
