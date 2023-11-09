package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchStack extends ItemStack {
  ItemType<ItemTorchStack> TORCH_TYPE = ItemTypeBuilder
          .builder(ItemTorchStack.class)
          .vanillaItem(VanillaItemId.TORCH)
          .build();
}
