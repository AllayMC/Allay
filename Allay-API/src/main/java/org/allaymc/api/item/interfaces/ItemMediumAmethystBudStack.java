package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMediumAmethystBudStack extends ItemStack {
  ItemType<ItemMediumAmethystBudStack> MEDIUM_AMETHYST_BUD_TYPE = ItemTypeBuilder
          .builder(ItemMediumAmethystBudStack.class)
          .vanillaItem(VanillaItemId.MEDIUM_AMETHYST_BUD)
          .build();
}
