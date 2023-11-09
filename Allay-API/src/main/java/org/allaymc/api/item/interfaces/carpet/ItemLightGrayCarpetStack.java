package org.allaymc.api.item.interfaces.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCarpetStack extends ItemStack {
  ItemType<ItemLightGrayCarpetStack> LIGHT_GRAY_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayCarpetStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_CARPET)
          .build();
}
