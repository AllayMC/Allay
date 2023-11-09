package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLargeAmethystBudStack extends ItemStack {
  ItemType<ItemLargeAmethystBudStack> LARGE_AMETHYST_BUD_TYPE = ItemTypeBuilder
          .builder(ItemLargeAmethystBudStack.class)
          .vanillaItem(VanillaItemId.LARGE_AMETHYST_BUD)
          .build();
}
