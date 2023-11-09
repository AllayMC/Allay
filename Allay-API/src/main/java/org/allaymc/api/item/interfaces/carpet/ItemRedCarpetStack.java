package org.allaymc.api.item.interfaces.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCarpetStack extends ItemStack {
  ItemType<ItemRedCarpetStack> RED_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemRedCarpetStack.class)
          .vanillaItem(VanillaItemId.RED_CARPET)
          .build();
}
