package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonBlockStack extends ItemStack {
  ItemType<ItemMelonBlockStack> MELON_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemMelonBlockStack.class)
          .vanillaItem(VanillaItemId.MELON_BLOCK)
          .build();
}
