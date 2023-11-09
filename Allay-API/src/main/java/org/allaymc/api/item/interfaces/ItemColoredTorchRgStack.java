package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemColoredTorchRgStack extends ItemStack {
  ItemType<ItemColoredTorchRgStack> COLORED_TORCH_RG_TYPE = ItemTypeBuilder
          .builder(ItemColoredTorchRgStack.class)
          .vanillaItem(VanillaItemId.COLORED_TORCH_RG)
          .build();
}
