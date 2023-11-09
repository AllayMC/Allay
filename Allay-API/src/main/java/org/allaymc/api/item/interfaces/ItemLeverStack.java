package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeverStack extends ItemStack {
  ItemType<ItemLeverStack> LEVER_TYPE = ItemTypeBuilder
          .builder(ItemLeverStack.class)
          .vanillaItem(VanillaItemId.LEVER)
          .build();
}
