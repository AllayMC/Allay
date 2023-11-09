package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateRedstoneOreStack extends ItemStack {
  ItemType<ItemDeepslateRedstoneOreStack> DEEPSLATE_REDSTONE_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateRedstoneOreStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_REDSTONE_ORE)
          .build();
}
