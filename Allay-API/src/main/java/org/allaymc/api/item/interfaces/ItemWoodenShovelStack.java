package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenShovelStack extends ItemStack {
  ItemType<ItemWoodenShovelStack> WOODEN_SHOVEL_TYPE = ItemTypeBuilder
          .builder(ItemWoodenShovelStack.class)
          .vanillaItem(VanillaItemId.WOODEN_SHOVEL)
          .build();
}
