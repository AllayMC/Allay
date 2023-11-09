package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNameTagStack extends ItemStack {
  ItemType<ItemNameTagStack> NAME_TAG_TYPE = ItemTypeBuilder
          .builder(ItemNameTagStack.class)
          .vanillaItem(VanillaItemId.NAME_TAG)
          .build();
}
