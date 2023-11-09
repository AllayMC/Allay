package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonebrickStack extends ItemStack {
  ItemType<ItemStonebrickStack> STONEBRICK_TYPE = ItemTypeBuilder
          .builder(ItemStonebrickStack.class)
          .vanillaItem(VanillaItemId.STONEBRICK)
          .build();
}
