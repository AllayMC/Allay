package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnlitRedstoneTorchStack extends ItemStack {
  ItemType<ItemUnlitRedstoneTorchStack> UNLIT_REDSTONE_TORCH_TYPE = ItemTypeBuilder
          .builder(ItemUnlitRedstoneTorchStack.class)
          .vanillaItem(VanillaItemId.UNLIT_REDSTONE_TORCH)
          .build();
}
