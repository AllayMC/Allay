package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitRedstoneOreStack extends ItemStack {
  ItemType<ItemLitRedstoneOreStack> LIT_REDSTONE_ORE_TYPE = ItemTypeBuilder
          .builder(ItemLitRedstoneOreStack.class)
          .vanillaItem(VanillaItemId.LIT_REDSTONE_ORE)
          .build();
}
