package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneTorchStack extends ItemStack {
  ItemType<ItemRedstoneTorchStack> REDSTONE_TORCH_TYPE = ItemTypeBuilder
          .builder(ItemRedstoneTorchStack.class)
          .vanillaItem(VanillaItemId.REDSTONE_TORCH)
          .build();
}
