package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArrowStack extends ItemStack {
  ItemType<ItemArrowStack> ARROW_TYPE = ItemTypeBuilder
          .builder(ItemArrowStack.class)
          .vanillaItem(VanillaItemId.ARROW)
          .build();
}
