package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBuddingAmethystStack extends ItemStack {
  ItemType<ItemBuddingAmethystStack> BUDDING_AMETHYST_TYPE = ItemTypeBuilder
          .builder(ItemBuddingAmethystStack.class)
          .vanillaItem(VanillaItemId.BUDDING_AMETHYST)
          .build();
}
