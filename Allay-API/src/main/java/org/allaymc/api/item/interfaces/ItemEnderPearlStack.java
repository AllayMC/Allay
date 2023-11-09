package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderPearlStack extends ItemStack {
  ItemType<ItemEnderPearlStack> ENDER_PEARL_TYPE = ItemTypeBuilder
          .builder(ItemEnderPearlStack.class)
          .vanillaItem(VanillaItemId.ENDER_PEARL)
          .build();
}
