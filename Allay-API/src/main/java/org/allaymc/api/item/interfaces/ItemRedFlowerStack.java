package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedFlowerStack extends ItemStack {
  ItemType<ItemRedFlowerStack> RED_FLOWER_TYPE = ItemTypeBuilder
          .builder(ItemRedFlowerStack.class)
          .vanillaItem(VanillaItemId.RED_FLOWER)
          .build();
}
