package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmeraldBlockStack extends ItemStack {
  ItemType<ItemEmeraldBlockStack> EMERALD_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemEmeraldBlockStack.class)
          .vanillaItem(VanillaItemId.EMERALD_BLOCK)
          .build();
}
