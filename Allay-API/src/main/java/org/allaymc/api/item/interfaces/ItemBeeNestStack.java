package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeeNestStack extends ItemStack {
  ItemType<ItemBeeNestStack> BEE_NEST_TYPE = ItemTypeBuilder
          .builder(ItemBeeNestStack.class)
          .vanillaItem(VanillaItemId.BEE_NEST)
          .build();
}
