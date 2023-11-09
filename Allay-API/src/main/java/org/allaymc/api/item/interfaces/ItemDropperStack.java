package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDropperStack extends ItemStack {
  ItemType<ItemDropperStack> DROPPER_TYPE = ItemTypeBuilder
          .builder(ItemDropperStack.class)
          .vanillaItem(VanillaItemId.DROPPER)
          .build();
}
