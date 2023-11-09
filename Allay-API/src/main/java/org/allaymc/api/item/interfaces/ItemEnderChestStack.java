package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderChestStack extends ItemStack {
  ItemType<ItemEnderChestStack> ENDER_CHEST_TYPE = ItemTypeBuilder
          .builder(ItemEnderChestStack.class)
          .vanillaItem(VanillaItemId.ENDER_CHEST)
          .build();
}
