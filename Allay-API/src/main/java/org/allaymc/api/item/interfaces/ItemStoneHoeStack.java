package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneHoeStack extends ItemStack {
  ItemType<ItemStoneHoeStack> STONE_HOE_TYPE = ItemTypeBuilder
          .builder(ItemStoneHoeStack.class)
          .vanillaItem(VanillaItemId.STONE_HOE)
          .build();
}
