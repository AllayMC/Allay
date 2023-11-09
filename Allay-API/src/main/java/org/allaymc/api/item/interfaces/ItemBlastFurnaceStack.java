package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlastFurnaceStack extends ItemStack {
  ItemType<ItemBlastFurnaceStack> BLAST_FURNACE_TYPE = ItemTypeBuilder
          .builder(ItemBlastFurnaceStack.class)
          .vanillaItem(VanillaItemId.BLAST_FURNACE)
          .build();
}
