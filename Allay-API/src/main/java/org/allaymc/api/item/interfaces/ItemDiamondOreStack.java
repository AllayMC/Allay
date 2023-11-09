package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondOreStack extends ItemStack {
  ItemType<ItemDiamondOreStack> DIAMOND_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDiamondOreStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_ORE)
          .build();
}
