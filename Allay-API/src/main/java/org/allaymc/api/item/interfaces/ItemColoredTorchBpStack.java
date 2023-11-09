package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemColoredTorchBpStack extends ItemStack {
  ItemType<ItemColoredTorchBpStack> COLORED_TORCH_BP_TYPE = ItemTypeBuilder
          .builder(ItemColoredTorchBpStack.class)
          .vanillaItem(VanillaItemId.COLORED_TORCH_BP)
          .build();
}
