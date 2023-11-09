package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRepeatingCommandBlockStack extends ItemStack {
  ItemType<ItemRepeatingCommandBlockStack> REPEATING_COMMAND_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemRepeatingCommandBlockStack.class)
          .vanillaItem(VanillaItemId.REPEATING_COMMAND_BLOCK)
          .build();
}
