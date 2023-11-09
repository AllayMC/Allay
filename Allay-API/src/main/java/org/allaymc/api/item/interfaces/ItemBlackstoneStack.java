package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneStack extends ItemStack {
  ItemType<ItemBlackstoneStack> BLACKSTONE_TYPE = ItemTypeBuilder
          .builder(ItemBlackstoneStack.class)
          .vanillaItem(VanillaItemId.BLACKSTONE)
          .build();
}
