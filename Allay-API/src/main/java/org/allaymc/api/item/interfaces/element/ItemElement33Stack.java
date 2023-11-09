package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement33Stack extends ItemStack {
  ItemType<ItemElement33Stack> ELEMENT_33_TYPE = ItemTypeBuilder
          .builder(ItemElement33Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_33)
          .build();
}
