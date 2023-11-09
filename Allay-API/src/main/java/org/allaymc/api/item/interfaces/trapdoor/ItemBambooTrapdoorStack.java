package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooTrapdoorStack extends ItemStack {
  ItemType<ItemBambooTrapdoorStack> BAMBOO_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemBambooTrapdoorStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_TRAPDOOR)
          .build();
}
