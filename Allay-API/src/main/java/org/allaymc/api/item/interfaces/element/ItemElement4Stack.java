package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement4Stack extends ItemStack {
  ItemType<ItemElement4Stack> ELEMENT_4_TYPE = ItemTypeBuilder
          .builder(ItemElement4Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_4)
          .build();
}
