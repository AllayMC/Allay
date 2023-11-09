package org.allaymc.api.item.interfaces.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaLeavesStack extends ItemStack {
  ItemType<ItemAzaleaLeavesStack> AZALEA_LEAVES_TYPE = ItemTypeBuilder
          .builder(ItemAzaleaLeavesStack.class)
          .vanillaItem(VanillaItemId.AZALEA_LEAVES)
          .build();
}
