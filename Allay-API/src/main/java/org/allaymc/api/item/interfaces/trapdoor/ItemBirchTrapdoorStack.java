package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchTrapdoorStack extends ItemStack {
  ItemType<ItemBirchTrapdoorStack> BIRCH_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemBirchTrapdoorStack.class)
          .vanillaItem(VanillaItemId.BIRCH_TRAPDOOR)
          .build();
}
