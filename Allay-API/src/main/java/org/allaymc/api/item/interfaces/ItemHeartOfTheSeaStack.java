package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeartOfTheSeaStack extends ItemStack {
  ItemType<ItemHeartOfTheSeaStack> HEART_OF_THE_SEA_TYPE = ItemTypeBuilder
          .builder(ItemHeartOfTheSeaStack.class)
          .vanillaItem(VanillaItemId.HEART_OF_THE_SEA)
          .build();
}
