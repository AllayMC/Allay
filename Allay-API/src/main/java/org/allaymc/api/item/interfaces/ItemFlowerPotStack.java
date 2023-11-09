package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowerPotStack extends ItemStack {
  ItemType<ItemFlowerPotStack> FLOWER_POT_TYPE = ItemTypeBuilder
          .builder(ItemFlowerPotStack.class)
          .vanillaItem(VanillaItemId.FLOWER_POT)
          .build();
}
