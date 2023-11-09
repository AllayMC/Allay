package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooStack extends ItemStack {
  ItemType<ItemBambooStack> BAMBOO_TYPE = ItemTypeBuilder
          .builder(ItemBambooStack.class)
          .vanillaItem(VanillaItemId.BAMBOO)
          .build();
}
