package org.allaymc.api.item.interfaces.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCarpetStack extends ItemStack {
  ItemType<ItemGrayCarpetStack> GRAY_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemGrayCarpetStack.class)
          .vanillaItem(VanillaItemId.GRAY_CARPET)
          .build();
}
