package org.allaymc.api.item.interfaces.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaLeavesFloweredStack extends ItemStack {
  ItemType<ItemAzaleaLeavesFloweredStack> AZALEA_LEAVES_FLOWERED_TYPE = ItemTypeBuilder
          .builder(ItemAzaleaLeavesFloweredStack.class)
          .vanillaItem(VanillaItemId.AZALEA_LEAVES_FLOWERED)
          .build();
}
