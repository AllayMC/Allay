package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDripstoneBlockStack extends ItemStack {
  ItemType<ItemDripstoneBlockStack> DRIPSTONE_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemDripstoneBlockStack.class)
          .vanillaItem(VanillaItemId.DRIPSTONE_BLOCK)
          .build();
}
