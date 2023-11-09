package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBigDripleafStack extends ItemStack {
  ItemType<ItemBigDripleafStack> BIG_DRIPLEAF_TYPE = ItemTypeBuilder
          .builder(ItemBigDripleafStack.class)
          .vanillaItem(VanillaItemId.BIG_DRIPLEAF)
          .build();
}
