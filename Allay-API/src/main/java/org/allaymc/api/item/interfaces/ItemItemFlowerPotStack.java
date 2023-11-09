package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemFlowerPotStack extends ItemStack {
  ItemType<ItemItemFlowerPotStack> ITEM_FLOWER_POT_TYPE = ItemTypeBuilder
          .builder(ItemItemFlowerPotStack.class)
          .vanillaItem(VanillaItemId.ITEM_FLOWER_POT)
          .build();
}
