package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmallAmethystBudStack extends ItemStack {
  ItemType<ItemSmallAmethystBudStack> SMALL_AMETHYST_BUD_TYPE = ItemTypeBuilder
          .builder(ItemSmallAmethystBudStack.class)
          .vanillaItem(VanillaItemId.SMALL_AMETHYST_BUD)
          .build();
}
