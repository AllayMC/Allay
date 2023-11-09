package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulTorchStack extends ItemStack {
  ItemType<ItemSoulTorchStack> SOUL_TORCH_TYPE = ItemTypeBuilder
          .builder(ItemSoulTorchStack.class)
          .vanillaItem(VanillaItemId.SOUL_TORCH)
          .build();
}
