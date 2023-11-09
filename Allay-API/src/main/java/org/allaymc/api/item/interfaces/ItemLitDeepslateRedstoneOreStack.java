package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitDeepslateRedstoneOreStack extends ItemStack {
  ItemType<ItemLitDeepslateRedstoneOreStack> LIT_DEEPSLATE_REDSTONE_ORE_TYPE = ItemTypeBuilder
          .builder(ItemLitDeepslateRedstoneOreStack.class)
          .vanillaItem(VanillaItemId.LIT_DEEPSLATE_REDSTONE_ORE)
          .build();
}
