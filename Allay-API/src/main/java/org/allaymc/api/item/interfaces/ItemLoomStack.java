package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLoomStack extends ItemStack {
  ItemType<ItemLoomStack> LOOM_TYPE = ItemTypeBuilder
          .builder(ItemLoomStack.class)
          .vanillaItem(VanillaItemId.LOOM)
          .build();
}
