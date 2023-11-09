package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrappedChestStack extends ItemStack {
  ItemType<ItemTrappedChestStack> TRAPPED_CHEST_TYPE = ItemTypeBuilder
          .builder(ItemTrappedChestStack.class)
          .vanillaItem(VanillaItemId.TRAPPED_CHEST)
          .build();
}
